package com.dharbor.sintaxterrors.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    public Integer code;
    public String message;
    public T content;

    public CommonResponse(T content) {
        this.content = content;
        code = HttpStatus.OK.value();
        message = HttpStatus.OK.name();
    }

    public CommonResponse(T content, HttpStatus code) {
        this.content = content;
        this.code = code.value();
        message = HttpStatus.OK.name();
    }

    public CommonResponse(T content, HttpStatus code, String message) {
        this.content = content;
        this.code = code.value();
        this.message = message;
    }
}
