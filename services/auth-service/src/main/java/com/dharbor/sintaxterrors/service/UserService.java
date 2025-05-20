package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.user.CreateUserRequest;
import com.dharbor.sintaxterrors.dto.request.user.GetUserPageRequest;
import com.dharbor.sintaxterrors.dto.request.user.UpdateUserRequest;
import com.dharbor.sintaxterrors.dto.response.user.UserEmployeeResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.model.entity.user.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    boolean isUserExist(String authEmail);

    Boolean isAValidUserName(String authEmail, Integer id);

    boolean isUserIdExist(Integer userId);

    void validateCreateUserRequest(CreateUserRequest createUserRequest);

    UserResponse saveUser(CreateUserRequest createUserRequest);

    void validateUpdateUserRequest(UpdateUserRequest updateUserRequest);

    UserResponse updateUser(UpdateUserRequest updateUserRequest);

    Page<UserEntity> findAll(Integer page, Integer size);

    UserResponse getUserById(Integer userId);

    UserResponse getAuthUserByUserName(String email);

    PaginationResponse<UserResponse> getUserPage(GetUserPageRequest request);

    Boolean hasTheRole(UserEntity user, String role);

    UserResponse registerUserLogin(String email);

    String validateAuthorization(String input);

    UserEmployeeResponse getUserByUsername(String input);
}
