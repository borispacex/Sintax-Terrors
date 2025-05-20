package com.dharbor.sintaxterrors.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommonPaginationResponse<T> {
    public Integer code;
    public String message;
    public PaginationResponse<T> content;

    public CommonPaginationResponse(PaginationResponse<T> content) {
        this.content = content;
        code = HttpStatus.OK.value();
        message = HttpStatus.OK.name();
    }

    public CommonPaginationResponse(PaginationResponse<T> content, HttpStatus code) {
        this.content = content;
        this.code = code.value();
        message = HttpStatus.OK.name();
    }

    public CommonPaginationResponse(PaginationResponse<T> content, HttpStatus code, String message) {
        this.content = content;
        this.code = code.value();
        this.message = message;
    }
}
