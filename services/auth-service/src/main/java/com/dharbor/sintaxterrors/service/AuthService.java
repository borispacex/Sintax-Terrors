package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.dto.request.auth.AuthRequest;
import com.dharbor.sintaxterrors.dto.request.auth.RefreshTokenRequest;
import com.dharbor.sintaxterrors.dto.response.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    Object refreshToken(RefreshTokenRequest refreshTokenRequest);
}
