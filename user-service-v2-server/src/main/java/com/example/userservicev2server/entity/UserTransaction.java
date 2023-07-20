package com.example.userservicev2server.entity;

import lombok.Data;
import lombok.ToString;
import org.parths.userservice.enums.UserTranStatus;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class UserTransaction {

    @Id
    private Integer id;

    private Integer userId;

    private Double amount;

    private UserTranStatus status;
}
