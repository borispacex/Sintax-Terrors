package com.dharbor.sintaxterrors.model.entity.user;

import com.dharbor.sintaxterrors.model.entity.role.RoleEntity;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserEntity.class)
public class UserEntity_ {

    public static volatile SingularAttribute<UserEntity, Integer> id;

    public static volatile SingularAttribute<UserEntity, String> username;

    public static volatile SingularAttribute<UserEntity, UserState> state;

    public static volatile SingularAttribute<UserEntity, RoleEntity> role;

}
