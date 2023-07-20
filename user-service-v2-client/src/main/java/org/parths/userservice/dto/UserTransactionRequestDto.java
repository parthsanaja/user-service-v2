package org.parths.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserTransactionRequestDto {

    private Integer id;

    private Integer userId;

    private Double amount;

}
