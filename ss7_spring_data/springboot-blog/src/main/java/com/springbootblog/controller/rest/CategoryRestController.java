package com.springbootblog.controller.rest;

import com.springbootblog.entity.Blog;
import com.springbootblog.entity.Category;
import com.springbootblog.service.impl.IBlogService;
import com.springbootblog.service.impl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBlogService blogService;

    // 1. Xem danh sách tất cả các category
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // 2. Xem danh sách các bài viết thuộc về một category
    @GetMapping("/{id}/blogs")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Blog> blogs = blogService.findAllByCategoryId(id);
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}
