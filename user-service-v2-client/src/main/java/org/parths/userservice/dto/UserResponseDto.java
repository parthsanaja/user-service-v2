package org.parths.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponseDto {

    private Integer id;

    private String name;

    private Double balance;
}
