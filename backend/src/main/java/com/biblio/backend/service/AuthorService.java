package com.biblio.backend.service;

import com.biblio.backend.entity.Author;
import com.biblio.backend.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository repository;

    public List<Author> findAll(){
        return repository.findAll();
    }

    public Author findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not Found: " + id));
    }

    @Transactional
    public Author create(Author author){
        return repository.save(author);
    }

    @Transactional
    public Author update(Long id, Author updated){
        Author existing = findById(id);
        existing.setName(updated.getName());
        existing.setNationality(updated.getNationality());
        existing.setBirthYear(updated.getBirthYear());
        existing.setBiography(updated.getBiography());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Author not Found: " + id);
        }
        repository.deleteById(id);
    }
}