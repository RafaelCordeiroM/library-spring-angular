package com.biblio.backend.dto.response;

import java.util.Set;

public record AuthorResponse(
        Long id,
        String name,
        String nationality,
        Integer birthYear,
        Set<BookSummaryResponse> books
) {}