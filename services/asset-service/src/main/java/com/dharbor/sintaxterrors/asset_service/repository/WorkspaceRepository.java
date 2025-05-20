package com.dharbor.sintaxterrors.asset_service.repository;


import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends
        JpaRepository<WorkspaceEntity, Long>,
        JpaSpecificationExecutor<WorkspaceEntity> {

    Optional<WorkspaceEntity> findById(@NonNull Long workspaceId);

    List<WorkspaceEntity> findByCity(@NonNull BoliviaCity city);

    List<WorkspaceEntity> findByCityAndLocation(BoliviaCity city, String location);

    Page<WorkspaceEntity> findAll(Pageable pageable);

    List<WorkspaceEntity> findAll(Specification<WorkspaceEntity> spec);

    List<WorkspaceEntity> findByIdIn(List<Long> ids);
}
