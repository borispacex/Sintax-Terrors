package com.dharbor.sintaxterrors.asset_service.repository;

import com.dharbor.sintaxterrors.asset_service.model.entity.request.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer>, JpaSpecificationExecutor<RequestEntity> {
    Optional<RequestEntity> findByTitle(String title);
}
