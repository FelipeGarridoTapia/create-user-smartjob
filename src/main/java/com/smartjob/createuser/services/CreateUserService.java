package com.smartjob.createuser.services;

import com.smartjob.createuser.constants.FieldConstant;
import com.smartjob.createuser.dtos.request.UserDto;
import com.smartjob.createuser.dtos.response.CreateUserResponse;
import com.smartjob.createuser.exceptions.CreateUserHttpException;
import com.smartjob.createuser.mappers.CreateUserMapper;
import com.smartjob.createuser.repository.UserRepository;
import com.smartjob.createuser.repository.model.User;
import com.smartjob.createuser.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserService implements ICreateUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserResponse registerUser(UserDto userDto) throws CreateUserHttpException {
        CreateUserResponse response;
        try {
            if (!EmailValidator.isValidEmail(userDto.getEmail())) {
                throw new CreateUserHttpException(FieldConstant.SAVE_ERROR,"Invalid email format");
            }
            if (userRepository.existsByEmail(userDto.getEmail())) {
                throw new CreateUserHttpException(FieldConstant.SAVE_ERROR,"Email already exists");
            }
            User userSet = CreateUserMapper.buildSave(userDto);
            User userSaved = userRepository.save(userSet);
            response = CreateUserMapper.buildResponse(userDto, userSaved);
        }catch(Exception e){
            throw new CreateUserHttpException(FieldConstant.SAVE_ERROR,e.getMessage());
        }
        return response;
    }

    @Override
    public CreateUserResponse getUsers() throws CreateUserHttpException {
        CreateUserResponse response;
        try {
            List<User> users = userRepository.findAll();
            response = CreateUserMapper.buildResponse(users);
        }catch(Exception e){
            throw new CreateUserHttpException(FieldConstant.FIND_ALL_ERROR,e.getMessage());
        }
        return response;
    }
}
