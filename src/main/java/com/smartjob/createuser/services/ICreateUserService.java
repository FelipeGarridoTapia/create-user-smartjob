package com.smartjob.createuser.services;


import com.smartjob.createuser.dtos.request.UserDto;
import com.smartjob.createuser.dtos.response.CreateUserResponse;
import com.smartjob.createuser.exceptions.CreateUserHttpException;

public interface ICreateUserService {
    CreateUserResponse registerUser(UserDto userDto) throws CreateUserHttpException;
    CreateUserResponse getUsers() throws CreateUserHttpException;
}
