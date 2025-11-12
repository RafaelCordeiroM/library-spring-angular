package com.biblio.backend.controller;



import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.entity.Book;
import com.biblio.backend.mapper.BookMapper;
import com.biblio.backend.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponse> findAll(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long authorId) {
        return service.findAll(categoryId, authorId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody Book book) {
        Book saved = service.create(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @Valid @RequestBody Book updated) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}