package com.springbootblog.service;

import com.springbootblog.entity.Blog;
import com.springbootblog.repository.IBlogRepository;
import com.springbootblog.service.impl.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> searchBlogs(String keyword, Long categoryId, Pageable pageable) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        boolean hasCategory = categoryId != null && categoryId > 0;

        if (hasKeyword && hasCategory) {
            return blogRepository.findByTitleContainingAndCategoryId(keyword.trim(), categoryId, pageable);
        } else if (hasKeyword) {
            return blogRepository.findByTitleContaining(keyword.trim(), pageable);
        } else if (hasCategory) {
            return blogRepository.findByCategoryId(categoryId, pageable);
        }
        return blogRepository.findAll(pageable);
    }
}
