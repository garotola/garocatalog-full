package com.garosuperior.dscatalog.dto;

import com.garosuperior.dscatalog.entities.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
