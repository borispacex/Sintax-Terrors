package com.dharbor.sintaxterrors.dto.response.auth;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuthResponse implements Serializable {
    private String token;
    private String refreshToken;

    public AuthResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
