package com.dharbor.sintaxterrors.model.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "create_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updatedAt;
}
