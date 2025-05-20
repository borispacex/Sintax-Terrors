package com.dharbor.sintaxterrors.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNotificationRequest
{
    private Integer userId;

    private String head;

    private String body;

    private String template;

    private Boolean isRead = false;
}
