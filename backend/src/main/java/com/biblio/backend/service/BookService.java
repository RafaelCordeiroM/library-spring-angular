package com.biblio.backend.service;

import com.biblio.backend.entity.Book;
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


    public List<Book> findAll(Long categoryId, Long authorId){
        if(categoryId != null) return repository.findByCategoryId(categoryId);
        if(authorId != null) return repository.findByAuthorsId(authorId);
        return repository.findAll();
    }

    public Book findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not Found: " + id));
    }

    @Transactional
    public Book create(Book book){
        return repository.save(book);
    }

//    @Transactional
//    public Book update(Long id, Book updated) {
//        Book existing = findById(id);
//        existing.setTitle(updated.getTitle());
//        existing.setIsbn(updated.getIsbn());
//        existing.setPublicationYear(updated.getPublicationYear());
//        existing.setEdition(updated.getEdition());
//        existing.setPages(updated.getPages());
//        existing.setSummary(updated.getSummary());
//        existing.setCoverImageUrl(updated.getCoverImageUrl());
//        existing.setCategory(updated.getCategory());
//        existing.setTotalCopies(updated.getTotalCopies());
//        existing.setAvailableCopies(updated.getAvailableCopies());
//        existing.getAuthors().clear();
//        existing.getAuthors().addAll(updated.getAuthors());
//        return repository.save(existing);
//    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Book not Found: " + id);
        }
        repository.deleteById(id);
    }
}
