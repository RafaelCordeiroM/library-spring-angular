package com.biblio.backend.service;

import com.biblio.backend.entity.Category;
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

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found: " + id));
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
            throw new RuntimeException("Category Not Found" + id);
        }
        repository.deleteById(id);
    }
}
