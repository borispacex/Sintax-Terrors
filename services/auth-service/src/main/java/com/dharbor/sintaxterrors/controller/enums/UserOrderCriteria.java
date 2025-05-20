package com.dharbor.sintaxterrors.controller.enums;

import com.dharbor.sintaxterrors.model.entity.user.UserEntity_;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserOrderCriteria {

    ID("ID", UserEntity_.id.getName()),
    USER_NAME("USER_NAME", UserEntity_.username.getName()),
    STATE("STATE", UserEntity_.state.getName()),
    ROLE_ID("ROLE_ID", UserEntity_.role.getName());

    private final String key;

    private final String value;

}
