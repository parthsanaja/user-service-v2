package com.example.userservicev2server.controller;

import com.example.userservicev2server.service.UserService;
import org.parths.userservice.dto.UserRequestDto;
import org.parths.userservice.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping("all")
    public Flux<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    public Mono<UserResponseDto> getUser(@PathVariable Integer userId){

        return userService.getUser(userId);
    }

    @PostMapping("")
    public Mono<ResponseEntity<UserResponseDto>> createUser(@RequestBody Mono<UserRequestDto> userRequestDtoMono){
        return userService.createUser(userRequestDtoMono)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserResponseDto>> updateUser(@PathVariable Integer id,
                                            @RequestBody Mono<UserRequestDto> userRequestDtoMono){
        return userService.updateUser(id, userRequestDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @DeleteMapping ("{id}")
    public Mono<Void> deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}

