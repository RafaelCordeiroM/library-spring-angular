package com.biblio.backend.service;

import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.entity.Book;
import com.biblio.backend.exception.ResourceNotFoundException;
import com.biblio.backend.mapper.BookMapper;
import com.biblio.backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    public List<BookResponse> findAll(Long categoryId, Long authorId){
        List<Book> books;
        if(categoryId != null) books = repository.findByCategoryId(categoryId);
        else if(authorId != null) books = repository.findByAuthorsId(authorId);
        else books = repository.findAll();

        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public BookResponse findById(Long id){
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));

        return bookMapper.toResponse(book);
    }

    @Transactional
    public Book create(Book book){
        return repository.save(book);
    }

    @Transactional
    public BookResponse update(Long id, Book updated) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));
        existing.setTitle(updated.getTitle());
        existing.setIsbn(updated.getIsbn());
        existing.setPublicationYear(updated.getPublicationYear());
        existing.setEdition(updated.getEdition());
        existing.setPages(updated.getPages());
        existing.setSummary(updated.getSummary());
        existing.setCoverImageUrl(updated.getCoverImageUrl());
        existing.setCategory(updated.getCategory());
        existing.setTotalCopies(updated.getTotalCopies());
        existing.setAvailableCopies(updated.getAvailableCopies());

        existing.getAuthors().clear();
        existing.getAuthors().addAll(updated.getAuthors());

        Book saved = repository.save(existing);
        return bookMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Book not Found: " + id);
        }
        repository.deleteById(id);
    }
}
