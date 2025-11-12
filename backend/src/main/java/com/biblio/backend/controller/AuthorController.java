package com.biblio.backend.controller;

import com.biblio.backend.dto.response.AuthorResponse;
import com.biblio.backend.entity.Author;
import com.biblio.backend.mapper.AuthorMapper;
import com.biblio.backend.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;
    private final AuthorMapper authorMapper;

    @GetMapping
    public List<AuthorResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody Author author) {
        Author saved = service.create(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id, @Valid @RequestBody Author updated) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}