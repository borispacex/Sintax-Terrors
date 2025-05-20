package com.dharbor.sintaxterrors.service;


import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.role.CreateRoleRequest;
import com.dharbor.sintaxterrors.dto.request.role.GetRolePageRequest;
import com.dharbor.sintaxterrors.dto.request.role.UpdateRoleRequest;
import com.dharbor.sintaxterrors.dto.response.role.RoleResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.RoleExceptionConstant;
import com.dharbor.sintaxterrors.mapper.BaseMapper;
import com.dharbor.sintaxterrors.mapper.RoleMapper;
import com.dharbor.sintaxterrors.model.entity.role.RoleEntity;
import com.dharbor.sintaxterrors.model.entity.role.RoleEntity_;
import com.dharbor.sintaxterrors.repository.RoleRepository;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Specifications;
import com.dharbor.sintaxterrors.util.function.Utils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class RoleServiceImpl implements RoleService {
    private RoleEntity preEntity;

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponse saveRole(CreateRoleRequest role) {
        RoleEntity newRole = roleMapper.mapperToRoleEntityFrom(role);
        return roleMapper.mapperToRoleResponseFrom(roleRepository.save(newRole));
    }

    @Override
    public void validateCreateRoleRequest(CreateRoleRequest role) {
        Optional<RoleEntity> roleEntity = roleRepository.findByName(role.getName());
        if (roleEntity.isPresent() && role.getName().equals(roleEntity.get().getName())) {
            throw new ProcessErrorException(String.format(RoleExceptionConstant.ROLE_ALREADY_EXISTS_MESSAGE,
                    role.getName()));
        }
    }

    @Override
    public RoleResponse updateRole(UpdateRoleRequest role) {
        roleMapper.updateRoleEntityFrom(role, preEntity);
        RoleEntity postEntity = roleRepository.save(preEntity);
        return roleMapper.mapperToRoleResponseFrom(postEntity);
    }

    @Override
    public void validateUpdateRoleRequest(UpdateRoleRequest role) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(role.getId());
        if (!roleEntity.isPresent()) {
            throw new ProcessErrorException(String.format(RoleExceptionConstant.ROLE_NOT_EXIST_MESSAGE, role.getId()));
        }
        preEntity = roleEntity.get();
    }

    @Override
    public RoleResponse getRoleById(Integer roleId) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(roleId);
        if (!roleEntity.isPresent()) {
            throw new ProcessErrorException(String.format(RoleExceptionConstant.ROLE_NOT_EXIST_MESSAGE, roleId));
        }
        return roleMapper.mapperToRoleResponseFrom(roleEntity.get());
    }

    @Override
    public PaginationResponse<RoleResponse> getRolePage(GetRolePageRequest request) {
        List<RoleResponse> contract = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<RoleEntity> pageResult = roleRepository.findAll(buildSpecification(request), pageRequest);
        contract.addAll(
                pageResult.getContent().stream()
                        .map(roleMapper::mapperToRoleResponseFrom)
                        .collect(Collectors.toList())
        );
        PaginationResponse<RoleResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(contract);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    private Specification<RoleEntity> buildSpecification(GetRolePageRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!Utils.isNull(request.getName())) {
                String keyWord = "%" + request.getName() + "%";
                predicates.add(criteriaBuilder.like(root.get(RoleEntity_.name), keyWord));
            }
            if (!Utils.isNull(request.getIsActive())) {
                predicates.add(criteriaBuilder.equal(root.get(RoleEntity_.isActive), request.getIsActive()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}