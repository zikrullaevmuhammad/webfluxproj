package com.example.webfluxdemodemo.lvl_4.external.service.implementation;

import com.example.webfluxdemodemo.lvl_1.domain.Book;
import com.example.webfluxdemodemo.lvl_1.domain.BookFlat;
import com.example.webfluxdemodemo.lvl_2.service.spi.AuthorService;
import com.example.webfluxdemodemo.lvl_2.service.spi.BookService;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.BookRepository;
import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.raw.BookRaw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    @Transactional(readOnly = true)
    public Flux<BookFlat> findFlatAll() {
        return bookRepository.findAll()
                .map(BookRaw::toBookFlat);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Book> findAll() {
        return findFlatAll()
                .flatMap(this::toDomain);
    }

    private Mono<Book> toDomain(BookFlat flat) {
        return Flux.fromIterable(flat.getAuthors())
                .flatMap(authorService::findFlatById)
                .collectList()
                .map(authors -> new Book(
                        flat.getId(),
                        flat.getTitle(),
                        flat.getYear(),
                        authors
                ));
    }
}
