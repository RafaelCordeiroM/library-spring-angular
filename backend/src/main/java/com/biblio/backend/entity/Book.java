package com.biblio.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String isbn;
    private Integer publicationYear;
    private String edition;
    private Integer pages;
    private String coverImageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer totalCopies = 1;
    private Integer availableCopies = 1;

    @ManyToMany
    @JoinTable(
            name="book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();
}
