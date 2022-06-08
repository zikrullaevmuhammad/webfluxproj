package com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.raw;

import com.example.webfluxdemodemo.lvl_1.domain.AuthorFlat;
import com.example.webfluxdemodemo.lvl_1.domain.BookFlat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("authors_with_books_id")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRaw {
    @Id
    private long id;
    private String name;
    private List<Long> books;

    public AuthorFlat toAuthorFlat() {
        return new AuthorFlat(id, name, books);
    }

    public static AuthorRaw from(AuthorFlat flat) {
        return new AuthorRaw(
                flat.getId(),
                flat.getName(),
                flat.getBooks());
    }

}
