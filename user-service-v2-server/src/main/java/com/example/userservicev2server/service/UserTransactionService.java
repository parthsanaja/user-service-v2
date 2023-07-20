package com.example.userservicev2server.service;

import com.example.userservicev2server.entity.UserTransaction;
import com.example.userservicev2server.mapper.UserTransactionMapper;
import com.example.userservicev2server.repository.UserRepository;
import com.example.userservicev2server.repository.UserTransactionRepository;
import org.parths.userservice.dto.UserTransactionRequestDto;
import org.parths.userservice.dto.UserTransactionResponseDto;
import org.parths.userservice.enums.UserTranStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserTransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @Autowired
    private UserTransactionMapper userTransactionMapper;


    public Mono<UserTransactionResponseDto> createTransaction(Mono<UserTransactionRequestDto> userTransactionRequestDtoMono){
        return userTransactionRequestDtoMono
                .flatMap(userTransactionRequestDto -> {
                    return validateAndSaveTransaction(userTransactionRequestDto);
                })
//                .map(savedUserTransaction -> userTransactionMapper.userTransactionToUserTransactionResponseDto(savedUserTransaction))
                ;
    }

    @Transactional
    public Mono<UserTransactionResponseDto> validateAndSaveTransaction(UserTransactionRequestDto userTransactionRequestDto) {
        return userRepository.findById(userTransactionRequestDto.getUserId())
                .flatMap(user -> {
                    UserTransaction userTransaction = userTransactionMapper.userTransactionRequestDtoToUserTransaction(userTransactionRequestDto);
                    userTransaction.setStatus(UserTranStatus.Success);
                    user.setBalance(user.getBalance() - userTransaction.getAmount());
                    return userRepository.save(user)
                            .flatMap( user1 -> {
                                if(user1.getBalance() < 0){
                                    throw new RuntimeException("Balance cannot be negative");
                                }
                                return userTransactionRepository.save(userTransaction);
                            });
                }).map(userTransaction -> userTransactionMapper.userTransactionToUserTransactionResponseDto(userTransaction))
        .onErrorResume( throwable -> {
                    UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();
                    userTransactionResponseDto.setUserId(userTransactionRequestDto.getUserId());
                    userTransactionResponseDto.setAmount(userTransactionRequestDto.getAmount());
                    userTransactionResponseDto.setMessage(throwable.getMessage());
                    userTransactionResponseDto.setStatus(UserTranStatus.Error);
                    return Mono.just(userTransactionResponseDto);
                }
        );
    }


    public Flux<UserTransactionResponseDto> getTransactionForUser(Integer userId) {
        return userTransactionRepository.findUserTransactionByUserId(userId)
                .map(userTransaction -> userTransactionMapper.userTransactionToUserTransactionResponseDto(userTransaction));
    }
}
