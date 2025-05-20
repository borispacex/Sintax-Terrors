package com.dharbor.sintaxterrors.controller.auth;

import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.AuthControllerConstant;
import com.dharbor.sintaxterrors.dto.request.auth.AuthRequest;
import com.dharbor.sintaxterrors.dto.request.auth.MeResponse;
import com.dharbor.sintaxterrors.dto.request.auth.RefreshTokenRequest;
import com.dharbor.sintaxterrors.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;


@Tag(name = AuthControllerConstant.AuthSwagger.TAG_NAME, description = AuthControllerConstant.AuthSwagger.TAG_DESCRIPTION)
@RequestScope
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PUBLIC_PATH + AuthControllerConstant.AuthPath.BASE_PATH)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = AuthControllerConstant.AuthSwagger.TAG_DESCRIPTION_LOGIN)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/refreshToken")
    @Operation(summary = AuthControllerConstant.AuthSwagger.TAG_DESCRIPTION_REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

}