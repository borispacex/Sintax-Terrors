package com.dharbor.sintaxterrors.asset_service.repository;

import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends
        JpaRepository<TeamEntity, Long>,
        JpaSpecificationExecutor<TeamEntity> {
    Optional<TeamEntity> findByName(String name);

    Optional<TeamEntity> findById(Integer id);
}
