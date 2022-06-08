package com.example.webfluxdemodemo.lvl_2.service.spi;

import com.example.webfluxdemodemo.lvl_1.domain.AuthorFlat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Mono<AuthorFlat> findFlatById(long id);

    Mono<AuthorFlat> save(AuthorFlat author);
}
