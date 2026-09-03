package com.springbootblog.service;

import com.springbootblog.entity.Blog;
import com.springbootblog.repository.IBlogRepository;
import com.springbootblog.service.impl.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        String cleanKeyword = StringUtils.hasText(keyword) ? keyword.trim() : null;
        return blogRepository.searchBlogs(cleanKeyword, categoryId, pageable);
    }

    @Override
    public List<Blog> findAllByCategoryId(Long categoryId) {
        return blogRepository.findAllByCategoryId(categoryId);
    }
}
