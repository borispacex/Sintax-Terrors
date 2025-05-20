package com.dharbor.sintaxterrors.dto.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponseDto {
    private String message;
    private boolean success;

    public EmailResponseDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}

