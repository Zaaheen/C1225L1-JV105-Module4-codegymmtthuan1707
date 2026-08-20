package com.springbootblog.repository;

import com.springbootblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<Blog, Long> {
    // Tìm kiếm bài viết theo tiêu đề (có phân trang)
    Page<Blog> findByTitleContaining(String title, Pageable pageable);

    // Lọc bài viết theo ID danh mục (có phân trang)
    Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);

    // Tìm kiếm bài viết theo tiêu đề VÀ lọc theo danh mục (có phân trang)
    Page<Blog> findByTitleContainingAndCategoryId(String title, Long categoryId, Pageable pageable);
}
