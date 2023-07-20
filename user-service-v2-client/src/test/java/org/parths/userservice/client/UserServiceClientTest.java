package org.parths.userservice.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

class UserServiceClientTest {

    @Value("user.service.url")
    private String userServiceUrlForTest;

    @Test
    void getAllUsers() {

        UserServiceClient client = new UserServiceClient(
                WebClient.builder().baseUrl("http://localhost:8092").build());
        StepVerifier.create(client.getAllUsers())
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void getUser() {
    }
}