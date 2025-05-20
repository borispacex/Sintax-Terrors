package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.CreateNotificationRequest;
import com.dharbor.sintaxterrors.dto.request.GetNotificationPageRequest;
import com.dharbor.sintaxterrors.dto.request.UpdateNotificationRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;

public interface NotificationService {
    NotificationResponse saveNotification(CreateNotificationRequest input);

    void validateCreateNotificationRequest(CreateNotificationRequest input);

    NotificationResponse updateNotification(UpdateNotificationRequest input);

    void validateUpdateNotificationRequest(UpdateNotificationRequest input);

    NotificationResponse getNotificationById(Long input);

    PaginationResponse<NotificationResponse> getNotificationPage(GetNotificationPageRequest input);
}