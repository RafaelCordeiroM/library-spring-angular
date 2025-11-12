package com.biblio.backend.dto.response;

import java.util.Set;

public record BookSummaryResponse(
        Long id,
        String title,
        String isbn,
        Integer publicationYear,
        String edition,
        Integer pages,
        String coverImageUrl,
        CategoryResponse category,
        Integer totalCopies,
        Integer availableCopies
) {

}