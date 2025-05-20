package com.dharbor.sintaxterrors.dto.email;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class EmailRequestDto {
    private String to;
    private String subject;
    private Map<String, Object> variables;
    private String provider;
}
