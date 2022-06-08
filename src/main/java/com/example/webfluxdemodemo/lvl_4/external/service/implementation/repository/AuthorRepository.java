package com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository;

import com.example.webfluxdemodemo.lvl_4.external.service.implementation.repository.raw.AuthorRaw;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AuthorRepository extends R2dbcRepository<AuthorRaw, Long> {

}
