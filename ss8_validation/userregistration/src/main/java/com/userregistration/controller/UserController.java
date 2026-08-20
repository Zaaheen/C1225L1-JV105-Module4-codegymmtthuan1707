package com.userregistration.controller;

import com.userregistration.entity.User;
import com.userregistration.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model) {

        // Nếu có lỗi Validate, giữ nguyên trang và hiển thị lỗi
        if (bindingResult.hasErrors()) {
            return "index";
        }

        // Nếu hợp lệ, tiến hành lưu vào CSDL
        userService.save(user);
        model.addAttribute("user", user);
        return "result";
    }
}
