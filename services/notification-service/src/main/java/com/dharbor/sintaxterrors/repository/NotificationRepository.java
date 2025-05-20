package com.dharbor.sintaxterrors.repository;

import com.dharbor.sintaxterrors.model.entity.NotificationEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NotificationRepository extends
        JpaRepository<NotificationEntity, Long>,
        JpaSpecificationExecutor<NotificationEntity> {
    Optional<NotificationEntity> findByUserId(@NonNull Integer userId);

}
