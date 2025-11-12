package com.biblio.backend.dto.response;

import java.util.Set;

public record AuthorSummaryResponse(
        Long id,
        String name,
        String nationality,
        Integer birthYear
) {}