package com.dharbor.sintaxterrors.exception.handler;

import com.dharbor.sintaxterrors.exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.dharbor.sintaxterrors.util.constant.ResponseConstant.BadRequestResponse.BAD_REQUEST_EXCEPTION;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CommandException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleCommandException(CommandException exception) {
        log.error("CommandException: {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST_EXCEPTION, exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ProcessErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleProcessErrorException(ProcessErrorException exception) {
        log.error("ProcessErrorException: {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST_EXCEPTION, exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationExceptionResponse> handleDtoValidationException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(
                new ValidationExceptionResponse(BAD_REQUEST_EXCEPTION, errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationExceptionResponse> handleParamsValidationException(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return new ResponseEntity<>(
                new ValidationExceptionResponse(BAD_REQUEST_EXCEPTION, errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST_EXCEPTION, exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST_EXCEPTION, exception.getCause().getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
        return new ResponseEntity<>(
            new ExceptionResponse(String.valueOf(HttpStatus.FORBIDDEN.value()), exception.getMessage()),
            new HttpHeaders(),
            HttpStatus.FORBIDDEN
        );
    }

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponse> handleProcessErrorException(UnauthorizedException exception) {
        log.error("UnauthorizedException: {}", exception.getMessage());
        return new ResponseEntity<>(
            new ExceptionResponse(String.valueOf(HttpStatus.FORBIDDEN.value()), exception.getMessage()),
            new HttpHeaders(),
            HttpStatus.FORBIDDEN
        );
    }

    @ResponseBody
    @ExceptionHandler(ExpiredTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionResponse> handleProcessErrorException(ExpiredTokenException exception) {
        log.error("ExpiredTokenException: {}", exception.getMessage());
        return new ResponseEntity<>(
            new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage()),
            new HttpHeaders(),
            HttpStatus.UNAUTHORIZED
        );
    }
}
