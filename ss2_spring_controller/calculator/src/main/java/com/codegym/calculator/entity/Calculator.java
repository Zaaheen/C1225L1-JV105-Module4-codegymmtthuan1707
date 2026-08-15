package com.codegym.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calculator {
    private double firstOperand;
    private double secondOperand;
    private String operator;
    private double result;
    private String errorMessage;
    private boolean success;

    public Calculator(double firstOperand, double secondOperand, String operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }
//    Calculator calc = Calculator.builder()
//            .firstOperand(firstOperand)
//            .secondOperand(secondOperand)
//            .operator(operator)
//            .build();
}
