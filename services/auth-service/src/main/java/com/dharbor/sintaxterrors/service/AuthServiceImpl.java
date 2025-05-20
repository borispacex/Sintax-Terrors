package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.dto.request.auth.AuthRequest;
import com.dharbor.sintaxterrors.dto.request.auth.RefreshTokenRequest;
import com.dharbor.sintaxterrors.dto.response.auth.AuthResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtService;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Credenciales inválidas", ex);
        }

        UserResponse user = userService.getAuthUserByUserName(request.getUsername());
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponse(token, refreshToken);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if (!jwtService.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh token inválido o expirado");
        }

        String username = jwtService.extractUsername(refreshToken);
        UserResponse user = userService.getAuthUserByUserName(username);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, refreshToken);
    }
}
