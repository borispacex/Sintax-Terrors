package com.dharbor.sintaxterrors.gateway_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExpiredTokenException extends ResponseStatusException
{
    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public ExpiredTokenException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
