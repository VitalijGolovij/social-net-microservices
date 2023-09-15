package ru.goloviy.profileservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class JwtTokenResponse {
    private final String token;
    private final Date exiparationDate;
}
