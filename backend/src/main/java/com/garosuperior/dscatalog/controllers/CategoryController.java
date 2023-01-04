package com.garosuperior.dscatalog.controllers;

import com.garosuperior.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @GetMapping()
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = new ArrayList<>();

        categories.add(new Category(1L, "Nova categoria"));
        categories.add(new Category(2L, "Nova categoria 2"));

        return ResponseEntity.ok().body(categories);
    }

}
