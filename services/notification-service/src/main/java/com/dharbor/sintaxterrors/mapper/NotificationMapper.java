package com.dharbor.sintaxterrors.mapper;

import com.dharbor.sintaxterrors.dto.request.CreateNotificationRequest;
import com.dharbor.sintaxterrors.dto.request.UpdateNotificationRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;
import com.dharbor.sintaxterrors.model.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
    NotificationEntity mapperToNotificationEntityFrom(CreateNotificationRequest source);

    NotificationResponse mapperToNotificationResponseFrom(NotificationEntity source);

    void updateNotificationEntityFrom(UpdateNotificationRequest source, @MappingTarget NotificationEntity target);
}