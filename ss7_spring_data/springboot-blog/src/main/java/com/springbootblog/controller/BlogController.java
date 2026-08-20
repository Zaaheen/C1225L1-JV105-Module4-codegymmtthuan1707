package com.springbootblog.controller;

import com.springbootblog.entity.Blog;
import com.springbootblog.service.impl.IBlogService;
import com.springbootblog.service.impl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    // Hiển thị danh sách bài viết có Phân trang, Sắp xếp theo ngày tạo giảm dần, Tìm kiếm & Lọc danh mục
    @GetMapping
    public String listBlogs(
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @PageableDefault(size = 4, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        Page<Blog> blogPage = blogService.searchBlogs(keyword, categoryId, pageable);

        model.addAttribute("blogs", blogPage);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", categoryId);

        return "blog/list";
    }

    // Form viết blog mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/create";
    }

    // Lưu bài blog mới
    @PostMapping("/create")
    public String saveBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Tạo bài viết mới thành công!");
        return "redirect:/blogs";
    }

    // Xem chi tiết bài blog (Bao gồm hiển thị tên Danh mục)
    @GetMapping("/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            model.addAttribute("blog", blogOptional.get());
            return "blog/view";
        } else {
            redirect.addFlashAttribute("error", "Không tìm thấy bài viết!");
            return "redirect:/blogs";
        }
    }

    // Form cập nhật bài blog
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            model.addAttribute("blog", blogOptional.get());
            model.addAttribute("categories", categoryService.findAll());
            return "blog/edit";
        } else {
            redirect.addFlashAttribute("error", "Không tìm thấy bài viết!");
            return "redirect:/blogs";
        }
    }

    // Xử lý cập nhật bài blog
    @PostMapping("/edit")
    public String updateBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Cập nhật bài viết thành công!");
        return "redirect:/blogs";
    }

    // Xóa bài blog
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirect) {
        blogService.remove(id);
        redirect.addFlashAttribute("message", "Xóa bài viết thành công!");
        return "redirect:/blogs";
    }
}
