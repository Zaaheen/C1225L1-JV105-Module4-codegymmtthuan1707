package com.codegym.calculator.controller;

import com.codegym.calculator.entity.Calculator;
import com.codegym.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String showCalculator() {
        return "calculator";
    }

    @RequestMapping(value = "/calculate", method = {RequestMethod.GET, RequestMethod.POST})
    public String calculate(
            @ModelAttribute("calculator") Calculator calculator,
            Model model) {

        // 1. Chuyển giao đối tượng Calculator đã bind dữ liệu cho Service Layer
        Calculator calculatorResult = calculatorService.calculate(calculator);

        // 2. Gửi Model đối tượng và thông số kết quả sang View (JSP)
        model.addAttribute("calculator", calculatorResult);
        model.addAttribute("firstOperand", calculatorResult.getFirstOperand());
        model.addAttribute("secondOperand", calculatorResult.getSecondOperand());
        model.addAttribute("operator", calculatorResult.getOperator());
        model.addAttribute("result", calculatorResult.getResult());
        model.addAttribute("errorMessage", calculatorResult.getErrorMessage());

        return "result";
    }
}
