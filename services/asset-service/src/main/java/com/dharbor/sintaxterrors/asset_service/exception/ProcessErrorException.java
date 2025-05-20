package com.dharbor.sintaxterrors.asset_service.exception;

public class ProcessErrorException extends RuntimeException {
    public ProcessErrorException(String message) {
        super(message);
    }

    public ProcessErrorException(Throwable cause) {
        super(cause);
    }

    public ProcessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ProcessErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
