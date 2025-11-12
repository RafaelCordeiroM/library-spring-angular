package com.biblio.backend.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class BookFilterRequest {

    @Size(max = 100)
    private String title;

    @Size(max = 13)
    private String isbn;

    private Integer publicationYear;

    @Positive
    private Long categoryId;

    @Positive
    private Long authorId;

    @Size(max = 100)
    private String authorName;

    @PositiveOrZero
    private Integer minPages;

    @Positive
    private Integer maxPages;

    @Min(0)
    private Integer page = 0;

    @Min(1)
    @Max(100)
    private Integer size = 10;

    @Pattern(regexp = "title|isbn|publicationYear|pages")
    private String sortBy = "title";

    @Pattern(regexp = "asc|desc|ASC|DESC")
    private String sortDir = "asc";

    public Pageable toPageable() {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        return PageRequest.of(page, size, sort);
    }
}