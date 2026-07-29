package com.example.calculate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

/**
 * Controller xử lý yêu cầu chuyển đổi tiền tệ từ USD sang VNĐ.
 */
@Controller
public class CalculateController {

    /**
     * Hiển thị trang chủ chứa form nhập thông tin chuyển đổi.
     */
    @GetMapping("/")
    public String showForm() {
        return "result";
    }

    /**
     * Xử lý yêu cầu tính toán quy đổi tiền tệ hỗ trợ cả phương thức GET và POST tại /calculate.
     * Người dùng có thể truy cập trực tiếp URL /calculate trên thanh địa chỉ trình duyệt
     * mà không bị chuyển hướng (redirect) hay báo lỗi 405 Method Not Allowed.
     *
     * @param rate Tỷ giá (VNĐ / 1 USD), mặc định là 25000 nếu không truyền
     * @param usd Số tiền USD cần quy đổi, mặc định là 0
     * @param model Đối tượng truyền dữ liệu sang View (JSP)
     * @return Tên view result (result.jsp)
     */
    @RequestMapping(value = "/calculate", method = {RequestMethod.GET, RequestMethod.POST})
    public String calculate(
            @RequestParam(name = "rate", defaultValue = "25000") double rate,
            @RequestParam(name = "usd", defaultValue = "0") double usd,
            Model model) {

        double vnd = rate * usd;

        // Định dạng số hiển thị đẹp mắt (ví dụ: 25,000,000)
        DecimalFormat formatter = new DecimalFormat("#,##0.##");
        String formattedVnd = formatter.format(vnd);
        String formattedUsd = formatter.format(usd);
        String formattedRate = formatter.format(rate);

        // Đưa các thông số nguyên bản vào Model để giữ lại giá trị trên input form
        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);

        // Đưa các thông số đã định dạng vào Model để hiển thị kết quả
        model.addAttribute("formattedRate", formattedRate);
        model.addAttribute("formattedUsd", formattedUsd);
        model.addAttribute("formattedVnd", formattedVnd);

        return "result";
    }
}