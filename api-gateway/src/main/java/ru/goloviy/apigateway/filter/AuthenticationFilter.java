package ru.goloviy.apigateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import ru.goloviy.apigateway.exception.InvalidJwtTokenException;
import ru.goloviy.apigateway.exception.MissingAuthorizationHeaderKey;
import ru.goloviy.apigateway.util.JwtUtil;

import java.util.List;
import java.util.function.Predicate;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(config.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new MissingAuthorizationHeaderKey();
                }
                String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }
                try {
                    jwtUtil.verifyToken(token);
                } catch (Exception e){
                    throw new InvalidJwtTokenException();
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{
        public final List<String> openApi = List.of(
                "/auth/register",
                "/auth/login",
                "/eureka"
        );
        public Predicate<ServerHttpRequest> isSecured =
                serverHttpRequest -> openApi.stream()
                        .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
    }
}
