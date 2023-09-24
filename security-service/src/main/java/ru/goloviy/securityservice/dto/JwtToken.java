package ru.goloviy.securityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class JwtToken {
    private String token;
    private Date exiparationDate;
}
