package ru.goloviy.securityservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.goloviy.securityservice.dto.JwtToken;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;

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

    public JwtToken generateToken(String username){
        Date exiparationDate = Date.from(ZonedDateTime.now().plusDays(daysLifeTime).toInstant());

        String jwt = JWT.create()
                .withSubject(subject)
                .withClaim(claim, username)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(exiparationDate)
                .sign(Algorithm.HMAC256(secret));
        return new JwtToken(jwt, exiparationDate);
    }

    public String getUsernameClaim(String token){
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaim(claim).asString();
    }

    public DecodedJWT verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .build();
        return verifier.verify(token);
    }
}
