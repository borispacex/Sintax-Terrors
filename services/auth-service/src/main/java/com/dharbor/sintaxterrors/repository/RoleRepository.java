package com.dharbor.sintaxterrors.repository;

import com.dharbor.sintaxterrors.model.entity.role.RoleEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends
        JpaRepository<RoleEntity, Integer>,
        JpaSpecificationExecutor<RoleEntity> {
    Optional<RoleEntity> findById(@NonNull Integer roleId);

    Optional<RoleEntity> findByName(@NonNull String name);

}
