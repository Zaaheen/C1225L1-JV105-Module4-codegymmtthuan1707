package com.thymeleafmanagementproduct.controller;

import com.thymeleafmanagementproduct.model.Product;
import com.thymeleafmanagementproduct.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * 1. Hiển thị danh sách sản phẩm & Tìm kiếm theo tên
     */
    @GetMapping
    public String index(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Product> productList;
        if (search != null && !search.trim().isEmpty()) {
            productList = productService.searchByName(search);
            model.addAttribute("search", search);
        } else {
            productList = productService.findAll();
        }
        model.addAttribute("products", productList);
        return "index";
    }

    /**
     * 2. Thêm mới sản phẩm (Hiển thị Form)
     */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    /**
     * Thêm mới sản phẩm (Xử lý Lưu)
     */
    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product, RedirectAttributes redirect) {
        productService.save(product);
        redirect.addFlashAttribute("success", "Thêm mới sản phẩm thành công!");
        return "redirect:/products";
    }

    /**
     * 3. Cập nhật thông tin sản phẩm (Hiển thị Form)
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "update";
    }

    /**
     * Cập nhật thông tin sản phẩm (Xử lý Lưu)
     */
    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
        return "redirect:/products";
    }

    /**
     * 4. Xóa một sản phẩm (Hiển thị xác nhận)
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "delete";
    }

    /**
     * Xóa một sản phẩm (Xử lý Xóa)
     */
    @PostMapping("/delete")
    public String remove(@ModelAttribute("product") Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Đã xóa sản phẩm thành công!");
        return "redirect:/products";
    }

    /**
     * 5. Xem chi tiết một sản phẩm
     */
    @GetMapping("/{id}/view")
    public String view(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "view";
    }
}