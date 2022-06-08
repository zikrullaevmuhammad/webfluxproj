package com.example.webfluxdemodemo.lvl_3.usecase.model.factory;

import com.example.webfluxdemodemo.lvl_1.domain.Book;
import com.example.webfluxdemodemo.lvl_3.usecase.model.BookModel;
import org.springframework.stereotype.Component;

@Component
public class BookModelFactory {
    public BookModel get(Book book) {
        return new BookModel(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getAuthors()
        );
    }
}
