package com.codegym.calculator.controller;

import com.codegym.calculator.entity.Calculator;
import com.codegym.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        if (!model.containsAttribute("calculator")) {
            model.addAttribute("calculator", new Calculator());
        }
        return "calculator";
    }

    @GetMapping("/result")
    public String showResult(Model model) {
        // Kiểm tra xem trong Model có chứa dữ liệu Flash Attribute từ lần POST trước không
        if (!model.containsAttribute("calculatorResult")) {
            // Nếu không có (do người dùng truy cập trực tiếp /result hoặc nhấn F5) -> Quay về trang chủ "/"
            return "redirect:/";
        }

        // Bóc tách đối tượng Calculator từ Flash Attribute để truyền sang View result.jsp
        Calculator result = (Calculator) model.getAttribute("calculatorResult");
        if (result != null) {
            model.addAttribute("firstOperand", result.getFirstOperand());
            model.addAttribute("secondOperand", result.getSecondOperand());
            model.addAttribute("operator", result.getOperator());
            model.addAttribute("result", result.getResult());
            model.addAttribute("errorMessage", result.getErrorMessage());
        }

        return "result";
    }

    @PostMapping("/calculate")
    public String calculate(
            @ModelAttribute("calculator") Calculator calculator,
            RedirectAttributes redirectAttributes) {

        // 1. Chuyển giao đối tượng Calculator cho Service xử lý tính toán
        Calculator result = calculatorService.calculate(calculator);

        // 2. Lưu đối tượng kết quả vào Flash Attribute (chỉ tồn tại trong 1 lần redirect)
        redirectAttributes.addFlashAttribute("calculatorResult", result);

        // 3. Redirect sang trang GET /result
        return "redirect:/result";
    }
}
