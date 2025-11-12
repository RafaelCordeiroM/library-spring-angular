package com.biblio.backend.repository;

import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository
        extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByAuthorsId(Long authorId);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Book> findByAuthorNameContainingIgnoreCase(String name);

    Long countByCategoryId(Long categoryId);
    Long countByAuthorsId(Long authorId);
}