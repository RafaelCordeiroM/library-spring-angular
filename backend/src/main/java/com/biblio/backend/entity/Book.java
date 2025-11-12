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

    // === GETTERS AND SETTERS ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

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