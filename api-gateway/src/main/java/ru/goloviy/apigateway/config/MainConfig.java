package ru.goloviy.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.goloviy.apigateway.filter.AuthenticationFilter;

@Configuration
public class MainConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder rlb,
                                     AuthenticationFilter filter) {

        return rlb.routes()
                .route(p -> p.path("/security/**")
                        .filters(f -> f.rewritePath("/security(?<segment>/?.*)", "$\\{segment}")
                                .filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://security"))
                .route(p -> p.path("/profile/**")
                        .filters(f -> f.rewritePath("/profile(?<segment>/?.*)", "$\\{segment}")
                                .filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))
                .route(p -> p.path("/message/**")
                        .filters(f -> f.rewritePath("/message(?<segment>/?.*)", "$\\{segment}")
                                .filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://message"))
                .route(p -> p.path("/file-storage/**")
                        .filters(f -> f.rewritePath("/file-storage(?<segment>/?.*)", "$\\{segment}")
                                .filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://file-storage"))
                .build();
    }
}
