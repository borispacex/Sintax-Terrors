package com.dharbor.sintaxterrors.exception;

public class ExpiredTokenException extends RuntimeException
{
    public ExpiredTokenException(String message) {
        super(message);
    }

    public ExpiredTokenException(Throwable cause) {
        super(cause);
    }

    public ExpiredTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
