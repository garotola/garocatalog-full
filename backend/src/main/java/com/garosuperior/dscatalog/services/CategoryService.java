package com.garosuperior.dscatalog.services;

import com.garosuperior.dscatalog.dto.CategoryDTO;
import com.garosuperior.dscatalog.entities.Category;
import com.garosuperior.dscatalog.repositories.CategoryRepository;
import com.garosuperior.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<CategoryDTO> categoryDTOList = repository.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());

        return categoryDTOList;
    }
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
        return new CategoryDTO(category);
    }
}
