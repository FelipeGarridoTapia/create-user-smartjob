package com.smartjob.createuser.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserResponse {

    private String message;
    private Object description;

    public CreateUserResponse(String message, Object description) {
        this.message = message;
        this.description = description;
    }
}
