package ru.goloviy.apigateway.exception.handler;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.goloviy.apigateway.exception.AuthException;
import ru.goloviy.apigateway.exception.InvalidJwtTokenException;
import ru.goloviy.apigateway.exception.MissingAuthorizationHeaderKey;

@Component
@Order(-2)
public class CustomExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (ex instanceof AuthException) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            String body = "";
            if (ex instanceof InvalidJwtTokenException) {
                body = "{\"error\": \"Invalid jwt token\"}";
            }
            if (ex instanceof MissingAuthorizationHeaderKey) {
                body = "{\"error\": \"Missing authorization header token\"}";
            }
            return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
        }
        return response.setComplete();
    }
}
