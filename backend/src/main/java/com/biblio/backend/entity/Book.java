package com.biblio.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "authors") // prevents infinite loop in logs
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title is mandatory")
    @Size(max = 200, message = "Title must have at most 200 characters")
    @Column(nullable = false, length = 200)
    private String title;

    @Size(max = 13, message = "ISBN must have at most 13 characters")
    @Column(unique = true, length = 13)
    private String isbn;

    @Min(value = 0, message = "Publication year must be positive")
    @Max(value = 9999, message = "Invalid publication year")
    private Integer publicationYear;

    @Size(max = 50, message = "Edition must have at most 50 characters")
    @Column(length = 50)
    private String edition;

    @Positive(message = "Number of pages must be positive")
    private Integer pages;

    @Size(max = 500, message = "Summary must have at most 500 characters")
    @Column(length = 500)
    private String summary;

    @Size(max = 500, message = "Cover image URL must have at most 500 characters")
    @Column(length = 500)
    private String coverImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "category_id")
    private Category category;

    @PositiveOrZero(message = "Total copies must be greater than or equal to zero")
    @Builder.Default
    private Integer totalCopies = 1;

    @PositiveOrZero(message = "Available copies must be greater than or equal to zero")
    @Builder.Default
    private Integer availableCopies = 1;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Builder.Default
    private Set<Author> authors = new HashSet<>();

    // === HELPER METHODS (recommended for ManyToMany) ===

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this); // synchronizes both sides
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void clearAuthors() {
        for (Author author : new HashSet<>(this.authors)) {
            removeAuthor(author);
        }
    }
}