package com.dharbor.sintaxterrors.dto.request;

import com.dharbor.sintaxterrors.exception.constant.NotificationExceptionConstant;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNotificationRequest
{
    @NotNull(message = NotificationExceptionConstant.NOT_NULL_ID)
    private Long id;

    private Integer userId;

    private String head;

    private String body;

    private String template;

    private Boolean isRead = false;
}
