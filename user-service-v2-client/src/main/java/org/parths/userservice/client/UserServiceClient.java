package org.parths.userservice.client;

import org.parths.userservice.dto.UserRequestDto;
import org.parths.userservice.dto.UserResponseDto;
import org.parths.userservice.dto.UserTransactionRequestDto;
import org.parths.userservice.dto.UserTransactionResponseDto;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserServiceClient {

    private WebClient webClient;

    public UserServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<UserResponseDto> getAllUsers(){
        return webClient.get()
                .uri("user/all")
                .exchangeToFlux(clientResponse -> {
                    return clientResponse.bodyToFlux(UserResponseDto.class);
                });
    }

    public Mono<UserResponseDto> getUser(Integer id){
        return webClient.get()
                .uri("user/{id}", id)
                .retrieve()
                .bodyToMono(UserResponseDto.class);
    }

    public Mono<UserResponseDto> createUser(UserRequestDto userRequestDto){
        return webClient.post()
                .uri("user")
                .bodyValue(userRequestDto)
                .retrieve()
                .bodyToMono(UserResponseDto.class);
    }

    public Mono<UserTransactionResponseDto> createUserTransaction(UserTransactionRequestDto userTransactionRequestDto){
        return webClient.post()
                .uri("user/transaction")
                .bodyValue(userTransactionRequestDto)
                .retrieve()
                .bodyToMono(UserTransactionResponseDto.class);
    }
}
