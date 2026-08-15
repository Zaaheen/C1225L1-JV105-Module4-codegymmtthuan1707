package com.springbootblog.controller;

import com.springbootblog.entity.Blog;
import com.springbootblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String listBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create")
    public String saveBlog(@ModelAttribute("blog") Blog blog,
                           RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Tạo bài viết mới thành công!");
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model,
                           RedirectAttributes redirect) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            model.addAttribute("blog", blogOptional.get());
            return "blog/view";
        } else {
            redirect.addFlashAttribute("error", "Không tìm thấy bài viết!");
            return "redirect:/blogs";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirect) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            model.addAttribute("blog", blogOptional.get());
            return "blog/edit";
        } else {
            redirect.addFlashAttribute("error", "Không tìm thấy bài viết!");
            return "redirect:/blogs";
        }
    }

    @PostMapping("/edit")
    public String updateBlog(@ModelAttribute("blog") Blog blog,
                             RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Cập nhật bài viết thành công!");
        return "redirect:/blogs";
    }

    // Bước 13: Xóa một bài blog
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id,
                             RedirectAttributes redirect) {
        blogService.remove(id);
        redirect.addFlashAttribute("message", "Xóa bài viết thành công!");
        return "redirect:/blogs";
    }
}
