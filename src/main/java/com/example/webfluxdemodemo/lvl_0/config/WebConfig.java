package com.example.webfluxdemodemo.lvl_0.config;

import com.example.webfluxdemodemo.lvl_4.external.controller.GetBooksController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
@RequiredArgsConstructor
public class WebConfig {
    private final GetBooksController controller;

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions.route(
                RequestPredicates.GET("/books"),
                controller::handle
        );
    }
}
