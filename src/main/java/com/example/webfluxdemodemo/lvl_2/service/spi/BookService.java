package com.example.webfluxdemodemo.lvl_2.service.spi;

import com.example.webfluxdemodemo.lvl_1.domain.Book;
import com.example.webfluxdemodemo.lvl_1.domain.BookFlat;
import reactor.core.publisher.Flux;

public interface BookService {
    Flux<BookFlat> findFlatAll();
    Flux<Book> findAll();
}
