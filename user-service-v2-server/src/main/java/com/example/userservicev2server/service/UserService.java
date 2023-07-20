package com.example.userservicev2server.service;

import com.example.userservicev2server.mapper.UserMapper;
import com.example.userservicev2server.repository.UserRepository;
import org.parths.userservice.dto.UserRequestDto;
import org.parths.userservice.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Mono<UserResponseDto> createUser(Mono<UserRequestDto> userRequestDtoMono){
        return userRequestDtoMono.map(userRequestDto -> userMapper.userRequestDtoToUser(userRequestDto))
                .flatMap(user -> userRepository.save(user))
                .map(user -> userMapper.userToUserReponseDto(user));
    }

    public Flux<UserResponseDto> getAllUsers(){
        return userRepository.findAll()
                .map(user -> userMapper.userToUserReponseDto(user));
    }

    public Mono<UserResponseDto> getUser(Integer id){
        return userRepository.findById(id)
                .map(user -> userMapper.userToUserReponseDto(user));
    }

    public Mono<UserResponseDto> updateUser(Integer id, Mono<UserRequestDto> userRequestDtoMono){
        return userRepository.findById(id)
                        .flatMap(
                                userFromDb -> {
                                    return userRequestDtoMono.map(userRequestDto -> userMapper.userRequestDtoToUser(userRequestDto))
                                            .flatMap(userFromRequest -> userRepository.save(userFromRequest))
                                            .map(savedUser -> userMapper.userToUserReponseDto(savedUser));
                                }
                        );

    }

    public Mono<Void> deleteUser(Integer id){
        return userRepository.deleteById(id);
    }


}
