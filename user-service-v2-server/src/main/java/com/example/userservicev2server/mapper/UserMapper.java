package com.example.userservicev2server.mapper;

import com.example.userservicev2server.entity.User;
import org.parths.userservice.dto.UserRequestDto;
import org.parths.userservice.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
//@Mapper(componentModel = "spring")
public class UserMapper {

/*    User userRequestDtoToUser(UserRequestDto userRequestDto);

    UserResponseDto userToUserReponseDto(User user);*/


    public User userRequestDtoToUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setId(userRequestDto.getId());
        user.setName(userRequestDto.getName());
        user.setBalance(userRequestDto.getBalance());
        return user;
    }

    public UserResponseDto userToUserReponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setBalance(user.getBalance());
        return userResponseDto;
    }
}
