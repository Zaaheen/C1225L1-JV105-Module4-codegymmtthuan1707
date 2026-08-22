package com.libraryapplication.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalLibraryExceptionHandler extends RuntimeException {

    @ExceptionHandler(BookOutOfStockException.class)
    public ModelAndView handleBookOutOfStockException(BookOutOfStockException ex) {
        ModelAndView mav = new ModelAndView("error-library");
        mav.addObject("errorType", "Hết Sách Trong Kho");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }
    @ExceptionHandler(InvalidTicketCodeException.class)
    public ModelAndView handleInvalidTicketCodeException(InvalidTicketCodeException ex) {
        ModelAndView mav = new ModelAndView("error-library");
        mav.addObject("errorType", "Mã Phiếu Mượn Không Hợp Lệ");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex) {
        ModelAndView mav = new ModelAndView("error-library");
        mav.addObject("errorType", "Lỗi Hệ Thống");
        mav.addObject("errorMessage", "Đã xảy ra lỗi không xác định: " + ex.getMessage());
        return mav;
    }
}
