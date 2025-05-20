package com.dharbor.sintaxterrors.mapper;


import com.dharbor.sintaxterrors.dto.request.user.CreateUserRequest;
import com.dharbor.sintaxterrors.dto.request.user.UpdateUserRequest;
import com.dharbor.sintaxterrors.dto.response.user.UserEmployeeResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.model.entity.user.UserEntity;
import com.dharbor.sintaxterrors.util.constant.ResponseConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {ResponseConstant.SuccessResponse.class})
public interface UserMapper {

    UserEntity mapperToUserEntityFrom(CreateUserRequest createUserRequest);

    default UserResponse mapperToUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setState(userEntity.getState());
        userResponse.setRoleName(userEntity.getRole().getName());
        userResponse.setRoleId(userEntity.getRole().getId());
        userResponse.setDateJoined(userEntity.getDateJoined());
        return userResponse;
    }

    default void mapperUserEntityToUpdate(UpdateUserRequest source, UserEntity target) {
        target.setPassword(!Utils.isNull(source.getPassword()) ? source.getPassword() : target.getPassword());
        target.setUsername(!Utils.isNull(source.getUsername()) ? source.getUsername() : target.getUsername());
        target.setState(!Utils.isNull(source.getState()) ? source.getState() : target.getState());
    }
}
