package com.example.webfluxdemodemo.lvl_3.usecase.model;

import com.example.webfluxdemodemo.lvl_1.domain.AuthorFlat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class BookModel {
    private final long id;
    private final String title;
    private final int year;
    private final List<AuthorFlat> authors;
}
