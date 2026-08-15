package com.codegym.calculator.service;

import com.codegym.calculator.entity.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public Calculator calculate(Calculator calc) {
        double firstOperand = calc.getFirstOperand();
        double secondOperand = calc.getSecondOperand();
        String operator = calc.getOperator() != null ? calc.getOperator() : "+";

        switch (operator) {
            case "+":
                calc.setResult(firstOperand + secondOperand);
                calc.setSuccess(true);
                break;
            case "-":
                calc.setResult(firstOperand - secondOperand);
                calc.setSuccess(true);
                break;
            case "*":
                calc.setResult(firstOperand * secondOperand);
                calc.setSuccess(true);
                break;
            case "/":
                if (secondOperand != 0) {
                    calc.setResult(firstOperand / secondOperand);
                    calc.setSuccess(true);
                } else {
                    calc.setErrorMessage("Không thể chia cho số 0 (Division by zero)!");
                    calc.setSuccess(false);
                }
                break;
            default:
                calc.setErrorMessage("Phép tính không hợp lệ: " + operator);
                calc.setSuccess(false);
                break;
        }

        return calc;
    }

    public Calculator calculate(double firstOperand, double secondOperand, String operator) {
        return calculate(new Calculator(firstOperand, secondOperand, operator));
    }
}
