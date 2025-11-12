package com.biblio.backend.mapper;

import com.biblio.backend.dto.response.AuthorResponse;
import com.biblio.backend.dto.response.AuthorSummaryResponse;
import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.dto.response.CategoryResponse;
import com.biblio.backend.entity.Author;
import com.biblio.backend.entity.Book;
import com.biblio.backend.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponse toResponse(Book book);

    AuthorResponse toAuthorResponse(Author author);

    AuthorSummaryResponse toAuthorSummaryResponse(Author author);

    CategoryResponse toCategoryResponse(Category category);
}