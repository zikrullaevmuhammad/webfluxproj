package com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository;

import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.raw.BookRaw;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BookRepository extends R2dbcRepository<BookRaw, Long> {
}
