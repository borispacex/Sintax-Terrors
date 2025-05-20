package com.dharbor.sintaxterrors.gateway_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException
{
    public UnauthorizedException() {
        super(HttpStatus.FORBIDDEN);
    }

    public UnauthorizedException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
