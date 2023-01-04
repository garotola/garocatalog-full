package com.garosuperior.dscatalog.services;

import com.garosuperior.dscatalog.entities.Category;
import com.garosuperior.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }
}