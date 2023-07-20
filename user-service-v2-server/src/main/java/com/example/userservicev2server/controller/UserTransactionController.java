package com.example.userservicev2server.controller;

import com.example.userservicev2server.service.UserTransactionService;
import org.parths.userservice.dto.UserTransactionRequestDto;
import org.parths.userservice.dto.UserTransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

    @Autowired
    private UserTransactionService userTransactionService;

    @PostMapping
    public Mono<UserTransactionResponseDto> createTransaction(@RequestBody Mono<UserTransactionRequestDto> userTransactionRequestDtoMono){
        return userTransactionService.createTransaction(userTransactionRequestDtoMono);
    }

    @GetMapping("/list/{userId}")
    public Flux<UserTransactionResponseDto> getTransactionForUser(@PathVariable Integer userId){
        return userTransactionService.getTransactionForUser(userId);

    }
}
