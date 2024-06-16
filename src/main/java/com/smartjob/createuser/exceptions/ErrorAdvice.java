package com.smartjob.createuser.exceptions;

import com.smartjob.createuser.dtos.response.CreateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(CreateUserHttpException.class)
    public ResponseEntity<CreateUserResponse> appException(CreateUserHttpException e) {
        CreateUserResponse errorResponse =
                CreateUserResponse.builder()
                        .message(String.valueOf(HttpStatus.BAD_REQUEST))
                        .description(e.getMessage())
                        .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CreateUserResponse> exception(Exception e) {
        CreateUserResponse errorResponse =
                CreateUserResponse.builder()
                        .message(String.valueOf(HttpStatus.BAD_REQUEST))
                        .description(e.getMessage())
                        .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
