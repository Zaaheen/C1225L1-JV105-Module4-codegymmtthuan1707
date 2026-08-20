package com.springbootblog.service.impl;

import com.springbootblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> searchBlogs(String keyword, Long categoryId, Pageable pageable);

    Optional<Blog> findById(Long id);

    Blog save(Blog blog);

    void remove(Long id);
}
