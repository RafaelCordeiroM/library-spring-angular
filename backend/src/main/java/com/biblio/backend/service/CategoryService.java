package com.biblio.backend.service;

import com.biblio.backend.entity.Category;
import com.biblio.backend.exception.EntityInUseException;
import com.biblio.backend.exception.ResourceNotFoundException;
import com.biblio.backend.repository.BookRepository;
import com.biblio.backend.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository repository;
    private final BookRepository bookRepository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found: " + id));
    }

    @Transactional
    public Category create(Category category){
        return repository.save(category);
    }

    @Transactional
    public Category update(Long id, Category updated){
        Category existing = findById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Category Not Found" + id);
        }

        if(bookRepository.countByCategoryId(id) > 0){
            throw new EntityInUseException("Cannot delete category with id " + id + " because it is associated with one or more books.");
        }

        repository.deleteById(id);
    }
}
