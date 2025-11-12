package com.biblio.backend.service;

import com.biblio.backend.dto.response.AuthorResponse;
import com.biblio.backend.entity.Author;
import com.biblio.backend.exception.EntityInUseException;
import com.biblio.backend.exception.ResourceNotFoundException;
import com.biblio.backend.mapper.AuthorMapper;
import com.biblio.backend.repository.AuthorRepository;
import com.biblio.backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository repository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorResponse> findAll(){
        return repository.findAll().stream().map(authorMapper::toResponse).toList();
    }

    public AuthorResponse findById(Long id){
        Author author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not Found: " + id));
        return authorMapper.toResponse(author);
    }

    @Transactional
    public Author create(Author author){
        return repository.save(author);
    }

    @Transactional
    public AuthorResponse update(Long id, Author updated){
        Author existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not Found: " + id));
        existing.setName(updated.getName());
        existing.setNationality(updated.getNationality());
        existing.setBirthYear(updated.getBirthYear());
        existing.setBiography(updated.getBiography());

        Author saved = repository.save(existing);
        return authorMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Author not Found: " + id);
        }
        if(bookRepository.countByAuthorsId(id) > 0){
            throw new EntityInUseException("Cannot delete author with id " + id + " because it is associated with one or more books.");
        }
        repository.deleteById(id);
    }
}