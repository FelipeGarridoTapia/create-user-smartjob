package com.smartjob.createuser.controllers;


import com.smartjob.createuser.dtos.request.UserDto;
import com.smartjob.createuser.dtos.response.CreateUserResponse;
import com.smartjob.createuser.exceptions.CreateUserHttpException;
import com.smartjob.createuser.services.ICreateUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = "/v1")
@Tag(name = "Create User API", description = "Evaluation API for SmartJob")
public class CreateUserController {

    private final ICreateUserService createUserService;

    public CreateUserController(ICreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping(path = "/register-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponse> registerUser(@Valid @RequestBody UserDto userDto)
            throws CreateUserHttpException {
        CreateUserResponse createUserResponse = createUserService.registerUser(userDto);
        return new ResponseEntity<>(createUserResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponse> getUsers() throws CreateUserHttpException {
        CreateUserResponse createUserResponse = createUserService.getUsers();
        return new ResponseEntity<>(createUserResponse, HttpStatus.OK);
    }
}