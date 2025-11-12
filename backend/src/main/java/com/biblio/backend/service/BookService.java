package com.biblio.backend.service;

import com.biblio.backend.dto.request.BookFilterRequest;
import com.biblio.backend.dto.response.BookResponse;
import com.biblio.backend.entity.Book;
import com.biblio.backend.exception.ResourceNotFoundException;
import com.biblio.backend.mapper.BookMapper;
import com.biblio.backend.repository.BookRepository;
import com.biblio.backend.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    public Page<BookResponse> findAll(BookFilterRequest filter){
        Specification<Book> spec = new BookSpecification(filter);
        Pageable pageable = filter.toPageable();

        Page<Book> page = repository.findAll(spec, pageable);
        return page.map(bookMapper::toResponse);


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
