package com.example.webfluxdemodemo.lvl_4.external.controller;

import com.example.webfluxdemodemo.lvl_3.usecase.book.GetBooksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetBooksController {
    private final GetBooksUseCase useCase;

    public Mono<ServerResponse> handle(ServerRequest req) {
        return useCase.get()
                .flatMap(list -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(list));
    }
}
