package com.example.webfluxdemodemo.lvl_3.usecase.book;

import com.example.webfluxdemodemo.lvl_2.service.spi.BookService;
import com.example.webfluxdemodemo.lvl_3.usecase.model.BookModel;
import com.example.webfluxdemodemo.lvl_3.usecase.model.factory.BookModelFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBooksUseCase {
    private final BookService bookService;
    private final BookModelFactory responseFactory;

    public Mono<List<BookModel>> get() {
        return bookService.findAll()
                .map(responseFactory::get)
                .collectList();
    }
}
