package com.dharbor.sintaxterrors.asset_service.exception.handler;

import com.dharbor.sintaxterrors.asset_service.exception.CommandException;
import com.dharbor.sintaxterrors.asset_service.exception.EmployeeRestException;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant.BadRequestResponse.BAD_REQUEST_EXCEPTION;


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
    @ExceptionHandler(EmployeeRestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ExceptionResponse> handleProcessErrorException(EmployeeRestException exception) {
        log.error("ProcessErrorException: {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST_EXCEPTION, exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationExceptionResponse> handleDtoValidationException(MethodArgumentNotValidException exception) {
        log.error("ValidationException: {}", exception.getMessage());
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
        log.error("ValidationException: {}", exception.getMessage());
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
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST_EXCEPTION, exception.getCause().getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
