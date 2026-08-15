package com.springbootblog.repository;

import com.springbootblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Long id(Long id);
}
