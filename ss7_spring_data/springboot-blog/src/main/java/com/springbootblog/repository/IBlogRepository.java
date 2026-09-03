package com.springbootblog.repository;

import com.springbootblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE " +
            "(:keyword IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:categoryId IS NULL OR :categoryId = 0 OR b.category.id = :categoryId)")
    Page<Blog> searchBlogs(@Param("keyword") String keyword,
                           @Param("categoryId") Long categoryId,
                           Pageable pageable);

    List<Blog> findAllByCategoryId(Long categoryId);
}
