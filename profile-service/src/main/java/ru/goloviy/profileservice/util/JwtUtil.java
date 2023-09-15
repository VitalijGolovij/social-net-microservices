package ru.goloviy.profileservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.daysLifeTime}")
    private int daysLifeTime;
    @Value("${jwt.subject}")
    private String subject;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.claim}")
    private String claim;

    public JwtTokenResponse generateToken(String username){
        Date exiparationDate = Date.from(ZonedDateTime.now().plusDays(daysLifeTime).toInstant());

        String jwt = JWT.create()
                .withSubject(subject)
                .withClaim(claim, username)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(exiparationDate)
                .sign(Algorithm.HMAC256(secret));
        return new JwtTokenResponse(jwt, exiparationDate);
    }

    public String getUsernameClaim(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim(claim).asString();
    }
}
