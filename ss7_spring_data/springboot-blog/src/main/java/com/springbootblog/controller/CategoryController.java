package com.springbootblog.controller;

import com.springbootblog.entity.Category;
import com.springbootblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("newCategory", new Category());
        return "category/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("newCategory") Category category,
                         RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Thêm danh mục mới thành công!");
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category/edit";
        } else {
            redirectAttributes.addFlashAttribute("error", "Danh mục không tồn tại!");
            return "redirect:/categories";
        }
    }

    @PostMapping("/edit")
    public String updateCategory(@ModelAttribute("category") Category category,
                                 RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Cập nhật danh mục thành công!");
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirect) {
        categoryService.remove(id);
        redirect.addFlashAttribute("message", "Xóa danh mục thành công!");
        return "redirect:/categories";
    }
}
