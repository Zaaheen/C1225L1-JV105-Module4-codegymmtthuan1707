package com.springbootblog.service.impl;

import com.springbootblog.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void remove(Long id);
}
