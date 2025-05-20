package com.dharbor.sintaxterrors.service;


import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.user.CreateUserRequest;
import com.dharbor.sintaxterrors.dto.request.user.GetUserPageRequest;
import com.dharbor.sintaxterrors.dto.request.user.UpdateUserRequest;
import com.dharbor.sintaxterrors.dto.response.user.EmployeeShortResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserEmployeeResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.RoleExceptionConstant;
import com.dharbor.sintaxterrors.exception.constant.UserExceptionConstant;
import com.dharbor.sintaxterrors.mapper.BaseMapper;
import com.dharbor.sintaxterrors.mapper.UserMapper;
import com.dharbor.sintaxterrors.model.entity.role.RoleEntity;
import com.dharbor.sintaxterrors.model.entity.role.RoleEntity_;
import com.dharbor.sintaxterrors.model.entity.user.UserEntity;
import com.dharbor.sintaxterrors.model.entity.user.UserEntity_;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import com.dharbor.sintaxterrors.repository.RoleRepository;
import com.dharbor.sintaxterrors.repository.UserRepository;
import com.dharbor.sintaxterrors.security.JwtUtil;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Specifications;
import com.dharbor.sintaxterrors.util.function.Utils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService {

    private UserEntity userEntityToUpdate;
    private RoleEntity roleToUserEntityToUpdate;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final EmployeeFeingService employeeFeingService;

    @Override
    public void validateCreateUserRequest(CreateUserRequest createUserRequest) {
        if (isUserExist(createUserRequest.getUsername())) {
            throw new ProcessErrorException(String.format(UserExceptionConstant.USER_ALREADY_EXISTS_MESSAGE,
                    createUserRequest.getUsername()));
        }
        if (Utils.isNull(createUserRequest.getState())) {
            createUserRequest.setState(UserState.ACTIVE);
        }
    }

    @Override
    public UserResponse saveUser(CreateUserRequest createUserRequest) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(createUserRequest.getRoleId());
        if (roleEntity.isEmpty()) {
            throw new ProcessErrorException(String.format(RoleExceptionConstant.ROLE_NOT_EXIST_MESSAGE,
                    createUserRequest.getRoleId()));
        }
        UserEntity userEntity = userMapper.mapperToUserEntityFrom(createUserRequest);
        userEntity.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        userEntity.setRole(roleEntity.get());
        userEntity.setDateJoined(LocalDateTime.now());
        userEntity.setUsername(createUserRequest.getUsername());
        return userMapper.mapperToUserResponse(userRepository.save(userEntity));
    }

    @Override
    public void validateUpdateUserRequest(UpdateUserRequest updateUserRequest) {
        if (!isAValidUserName(updateUserRequest.getUsername(), updateUserRequest.getId())) {
            throw new ProcessErrorException(UserExceptionConstant.EMAIL_ALREADY_EXIST_MESSAGE);
        }

        Optional<UserEntity> userEntity = userRepository.findById(updateUserRequest.getId());
        if (userEntity.isEmpty()) {
            throw new ProcessErrorException(String.format(UserExceptionConstant.USER_NOT_EXIST_MESSAGE,
                    updateUserRequest.getId()));
        }
        userEntityToUpdate = userEntity.get();

        if (!Utils.isNull(updateUserRequest.getRoleId())) {
            Optional<RoleEntity> roleEntity = roleRepository.findById(updateUserRequest.getRoleId());
            if (roleEntity.isEmpty()) {
                throw new ProcessErrorException(String.format(RoleExceptionConstant.ROLE_NOT_EXIST_MESSAGE,
                        updateUserRequest.getRoleId()));
            }
            roleToUserEntityToUpdate = roleEntity.get();
        }
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        if (!Utils.isNull(updateUserRequest.getPassword())) {
            updateUserRequest.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }
        this.userMapper.mapperUserEntityToUpdate(updateUserRequest, userEntityToUpdate);
        if (!Utils.isNull(roleToUserEntityToUpdate)) {
            userEntityToUpdate.setRole(roleToUserEntityToUpdate);
        }
        userEntityToUpdate.setUsername(updateUserRequest.getUsername());
        return userMapper.mapperToUserResponse(userRepository.save(userEntityToUpdate));
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public Boolean isAValidUserName(String userName, Integer id) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(userName);
        return userEntity.isEmpty() || userEntity.get().getId().equals(id);
    }

    @Override
    public boolean isUserIdExist(Integer userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity.isPresent();
    }

    @Override
    public Page<UserEntity> findAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page - 1, size);
        return userRepository.findAll(paging);
    }

    @Override
    public UserResponse getUserById(Integer userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            throw new ProcessErrorException(String.format(UserExceptionConstant.USER_NOT_EXIST_MESSAGE, userId));
        }
        return userMapper.mapperToUserResponse(userEntity.get());
    }

    @Override
    public UserResponse getAuthUserByUserName(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new ProcessErrorException(String.format(UserExceptionConstant.USER_USERNAME_NOT_EXIST_MESSAGE, username)));
        return userMapper.mapperToUserResponse(userEntity);
    }

    @Override
    public UserResponse registerUserLogin(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new ProcessErrorException(String.format(UserExceptionConstant.USER_EMAIL_NOT_EXIST_MESSAGE, username)));
        userRepository.save(userEntity);
        return userMapper.mapperToUserResponse(userEntity);
    }

    @Override
    public String validateAuthorization(String authorization) {
        String token = authorization.replace("Bearer ", "");
        if(!jwtUtil.validateToken(token)) {
            throw new ProcessErrorException(UserExceptionConstant.INVALID_TOKEN_MESSAGE);
        }
        String username = jwtUtil.extractUsername(token);
        if (Utils.isNull(username)) {
            throw new ProcessErrorException(String.format(UserExceptionConstant.USER_USERNAME_NOT_EXIST_MESSAGE, username));
        }
        return username;
    }

    @Override
    public UserEmployeeResponse getUserByUsername(String input) {
        UserEntity userEntity = userRepository.findByUsername(input)
                .orElseThrow(() -> new ProcessErrorException(String.format(UserExceptionConstant.USER_USERNAME_NOT_EXIST_MESSAGE, input)));

        CommonResponse<EmployeeShortResponse> response = employeeFeingService.getEmployeeByUserId(userEntity.getId());
        UserEmployeeResponse userEmployeeResponse = new UserEmployeeResponse();
        userEmployeeResponse.setUsername(userEntity.getUsername());
        userEmployeeResponse.setEmail(response.getContent().getPersonalEmail());
        userEmployeeResponse.setId(userEntity.getId());
        userEmployeeResponse.setEmployeeId(response.getContent().getId());
        userEmployeeResponse.setRole(userEntity.getRole().getName());
        return userEmployeeResponse;
    }

    @Override
    public PaginationResponse<UserResponse> getUserPage(GetUserPageRequest request) {
        String order = (!Utils.isNull(request.getOrder())) ? request.getOrder().getValue() : null;
        Sort sort = Specifications.buildSorting(order, request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<UserEntity> pageResult = userRepository.findAll(buildSpecification(request), pageRequest);

        List<UserResponse> users = pageResult.getContent().stream()
                .map(userMapper::mapperToUserResponse)
                .collect(Collectors.toList());

        PaginationResponse<UserResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(users);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    private Specification<UserEntity> buildSpecification(GetUserPageRequest request) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!Utils.isNull(request.getState())) {
                predicates.add(builder.equal(root.get(UserEntity_.state), request.getState()));
            }

            if (!Utils.isNull(request.getUsername())) {
                String newSearch = "%" + request.getUsername() + "%";
                predicates.add(builder.or(
                        builder.like(root.get(UserEntity_.username), newSearch)
                ));
            }

            if (!Utils.isNull(request.getRoleId())) {
                Join<UserEntity, RoleEntity> userJoinRole = root.join(UserEntity_.role);
                predicates.add(builder.equal(userJoinRole.get(RoleEntity_.id), request.getRoleId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Boolean hasTheRole(UserEntity user, String role) {
        return user.getRole().getName().equals(role);
    }
}
