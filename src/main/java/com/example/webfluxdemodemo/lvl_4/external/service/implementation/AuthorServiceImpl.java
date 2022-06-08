package com.example.webfluxdemodemo.lvl_4.external.service.implementation;

import com.example.webfluxdemodemo.lvl_1.domain.AuthorFlat;
import com.example.webfluxdemodemo.lvl_1.domain.Book;
import com.example.webfluxdemodemo.lvl_2.service.spi.AuthorService;
import com.example.webfluxdemodemo.lvl_2.service.spi.BookService;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.cache.AuthorsCache;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.AuthorRepository;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.BookRepository;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.raw.AuthorRaw;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.raw.BookRaw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.cache.CacheMono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final TransactionOperations transactionOperations;
    private final AuthorsCache cache;

    @Override
    public Mono<AuthorFlat> findFlatById(long id) {
        return CacheMono.lookup(cache.getCache().asMap(), id)
                .onCacheMissResume(transactionOperations.findFlatById(id));
    }

    @Override
    public Mono<AuthorFlat> save(AuthorFlat author) {
        return findFlatById(author.getId()).map(cache::evict)

                .flatMap(a -> Mono.just(AuthorRaw.from(a))
                        .flatMap(authorRepository::save)
                        .map(AuthorRaw::toAuthorFlat))

                .map(cache::evict);
    }

    @Service
    @RequiredArgsConstructor
    public static class TransactionOperations {
        private final AuthorRepository authorRepository;

        @Transactional(readOnly = true)
        public Mono<AuthorFlat> findFlatById(long id) {
            return authorRepository.findById(id)
                    .map(AuthorRaw::toAuthorFlat)
                    .doOnNext(a -> System.out.println("FROM DB: " + a));
        }
    }
}
