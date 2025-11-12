package com.biblio.backend.dto.response;

import java.util.Set;

public record BookResponse(
        Long id,
        String title,
        String isbn,
        Integer publicationYear,
        String edition,
        Integer pages,
        String coverImageUrl,
        CategoryResponse category,
        Integer totalCopies,
        Integer availableCopies,
        Set<AuthorSummaryResponse> authors
) {

}