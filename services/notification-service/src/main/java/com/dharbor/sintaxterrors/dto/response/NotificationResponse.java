package com.dharbor.sintaxterrors.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {
    private Long id;

    private Integer userId;

    private String head;

    private String body;

    private String template;

    private Boolean isRead;
}
