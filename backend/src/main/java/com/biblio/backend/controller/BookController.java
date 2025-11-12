package com.biblio.backend.controller;



import com.biblio.backend.dto.request.BookFilterRequest;
import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.entity.Book;
import com.biblio.backend.mapper.BookMapper;
import com.biblio.backend.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<BookResponse>> findAll(BookFilterRequest filter) {
        Page<BookResponse> page = service.findAll(filter);
        return ResponseEntity.ok(page);
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