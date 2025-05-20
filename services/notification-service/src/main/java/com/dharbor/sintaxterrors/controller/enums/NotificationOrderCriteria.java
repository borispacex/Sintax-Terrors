package com.dharbor.sintaxterrors.controller.enums;


import com.dharbor.sintaxterrors.model.entity.NotificationEntity_;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationOrderCriteria {

    ID("ID", NotificationEntity_.id.getName()),
    NAME("HEAD", NotificationEntity_.head.getName()),
    IS_ACTIVE("IS_READ", NotificationEntity_.isRead.getName());

    private final String key;

    private final String value;

}
