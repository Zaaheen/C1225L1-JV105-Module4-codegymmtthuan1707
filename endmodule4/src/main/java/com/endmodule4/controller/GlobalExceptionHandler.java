package com.endmodule4.controller;

import com.endmodule4.exception.QuestionNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String SAFE_MESSAGE = "Xin lỗi, yêu cầu của bạn đang được xử lý, vui lòng đợi trong giây lát.";

    @ExceptionHandler(QuestionNotFoundException.class)
    public Object handleNotFound(HttpServletRequest request) {
        if (isAjax(request)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", SAFE_MESSAGE));
        }
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("message", SAFE_MESSAGE);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public Object handleUnexpectedException(HttpServletRequest request) {
        if (isAjax(request)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", SAFE_MESSAGE));
        }
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("message", SAFE_MESSAGE);
        return modelAndView;
    }

    private boolean isAjax(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        String accept = request.getHeader("Accept");
        return "XMLHttpRequest".equalsIgnoreCase(requestedWith)
                || (accept != null && accept.contains("application/json"));
    }
}
