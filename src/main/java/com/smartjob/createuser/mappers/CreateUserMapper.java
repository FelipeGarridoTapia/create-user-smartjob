package com.smartjob.createuser.mappers;

import com.smartjob.createuser.constants.FieldConstant;
import com.smartjob.createuser.dtos.request.PhoneDto;
import com.smartjob.createuser.dtos.request.UserDto;
import com.smartjob.createuser.dtos.response.CreateUserResponse;
import com.smartjob.createuser.exceptions.CreateUserHttpException;
import com.smartjob.createuser.repository.model.Phone;
import com.smartjob.createuser.repository.model.User;
import com.smartjob.createuser.utils.Util;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CreateUserMapper {

    private CreateUserMapper(){}

    public static User buildSave(UserDto userRequest){
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(getPhones(userRequest.getPhones()))
                .build();
    }

    private static List<Phone> getPhones(List<PhoneDto> phoneDtos) {
        return phoneDtos.stream().map(phoneDto ->
                Phone.builder()
                        .number(phoneDto.getNumber())
                        .cityCode(phoneDto.getCityCode())
                        .countryCode(phoneDto.getCountryCode())
                        .build()
        ).toList();
    }

    public static CreateUserResponse buildResponse(UserDto userRequest, User user) throws CreateUserHttpException {
        if(!ObjectUtils.isEmpty(user)){
            userRequest.setId(user.getId());
            userRequest.setCreated(Util.convertTimestampToString(user.getCreated()));
            userRequest.setModified(Util.convertTimestampToString(user.getModified()));
            userRequest.setLastLogin(Util.convertTimestampToString(user.getLastLogin()));
            userRequest.setToken(user.getToken());
            userRequest.setIsActive(user.getIsActive());
            return CreateUserResponse.builder()
                    .message(FieldConstant.SAVE_SUCCESS)
                    .description(userRequest)
                    .build();
        }
        throw new CreateUserHttpException(FieldConstant.SAVE_ERROR, "User saved is null");
    }

    public static CreateUserResponse buildResponse(List<User> users){
        String message = FieldConstant.USERS_NOT_FOUND;
        if(!ObjectUtils.isEmpty(users)){
            message = FieldConstant.USERS_FOUND;
        }
        return CreateUserResponse.builder()
                .message(message)
                .description(users)
                .build();
    }

}
