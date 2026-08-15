package com.springbootblog.service.impl;

import com.springbootblog.entity.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog save(Blog blog);
    void remove(Long id);
}
