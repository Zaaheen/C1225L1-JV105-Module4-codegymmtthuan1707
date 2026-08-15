package com.codegym.sandwich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam(value = "condiment", required = false) String[] condiment,
            Model model) {

        if (condiment != null && condiment.length > 0) {
            model.addAttribute("selectedCondiments", condiment);
        } else {
            model.addAttribute("message", "Bạn chưa chọn gia vị nào cho Sandwich!");
        }

        return "result";
    }
}
