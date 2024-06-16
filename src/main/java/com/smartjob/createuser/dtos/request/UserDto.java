package com.smartjob.createuser.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDto {
    private String name;
    @NotBlank(message = "Email is mandatory")
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private UUID id;
    private String created;
    private String modified;
    private String lastLogin;
    private UUID token;
    private Boolean isActive;
}
