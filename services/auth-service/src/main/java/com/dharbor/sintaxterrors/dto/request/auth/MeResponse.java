package com.dharbor.sintaxterrors.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MeResponse implements Serializable {
    private String username;
    private String email;
    private Integer id;
}
