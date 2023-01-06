package com.garosuperior.dscatalog.services;

import com.garosuperior.dscatalog.dto.CategoryDTO;
import com.garosuperior.dscatalog.entities.Category;
import com.garosuperior.dscatalog.repositories.CategoryRepository;
import com.garosuperior.dscatalog.services.exceptions.DataBaseException;
import com.garosuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
        Page<CategoryDTO> categoryDTOList = repository.findAll(pageRequest)
                .map(CategoryDTO::new);
        return categoryDTOList;
    }
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        return new CategoryDTO(category);
    }
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException err) {
            throw new ResourceNotFoundException("Id not found " + id);
        }catch (DataIntegrityViolationException err){
            throw new DataBaseException("Integrity violation");
        }
    }
}
