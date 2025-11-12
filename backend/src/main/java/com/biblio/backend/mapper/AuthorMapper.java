package com.biblio.backend.mapper;

import com.biblio.backend.dto.response.AuthorResponse;
import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.entity.Author;
import com.biblio.backend.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponse toResponse(Author author);

    BookResponse toBookResponse(Book book);
}