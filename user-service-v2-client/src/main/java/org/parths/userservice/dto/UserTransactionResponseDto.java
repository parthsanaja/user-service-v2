package org.parths.userservice.dto;

import lombok.Data;
import lombok.ToString;
import org.parths.userservice.enums.UserTranStatus;

@Data
@ToString
public class UserTransactionResponseDto {

    private Integer id;

    private Integer userId;

    private Double amount;

    private UserTranStatus status;

    private String message;

}
