package com.example.userservicev2server.mapper;

import com.example.userservicev2server.entity.UserTransaction;
import org.parths.userservice.dto.UserTransactionRequestDto;
import org.parths.userservice.dto.UserTransactionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserTransactionMapper {

/*    User userRequestDtoToUser(UserRequestDto userRequestDto);

    UserResponseDto userToUserReponseDto(User user);*/


    public UserTransaction userTransactionRequestDtoToUserTransaction(UserTransactionRequestDto userTransactionRequestDto){
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setId(userTransactionRequestDto.getId());
        userTransaction.setUserId(userTransactionRequestDto.getUserId());
        userTransaction.setAmount(userTransactionRequestDto.getAmount());
        return userTransaction;
    }

    public UserTransactionResponseDto userTransactionToUserTransactionResponseDto(UserTransaction userTransaction){
        UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();
        userTransactionResponseDto.setId(userTransaction.getId());
        userTransactionResponseDto.setUserId(userTransaction.getUserId());
        userTransactionResponseDto.setAmount(userTransaction.getAmount());
        userTransactionResponseDto.setStatus(userTransaction.getStatus());
        return userTransactionResponseDto;

    }
}
