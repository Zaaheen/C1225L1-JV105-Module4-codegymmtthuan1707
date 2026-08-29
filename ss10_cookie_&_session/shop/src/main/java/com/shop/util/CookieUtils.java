package com.shop.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CookieUtils {

    private static final String CART_COOKIE_NAME = "COOKIE_CART";
    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // Lưu Cookie trong 7 ngày

    // Đọc chuỗi Cookie giỏ hàng từ Browser
    public static String getCartFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CART_COOKIE_NAME.equals(cookie.getName())) {
                    return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                }
            }
        }
        return "";
    }

    // Ghi chuỗi Cookie giỏ hàng xuống Browser
    public static void saveCartToCookie(String cartData, HttpServletResponse response) {
        String encodedValue = URLEncoder.encode(cartData, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie(CART_COOKIE_NAME, encodedValue);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setPath("/"); // Áp dụng cho toàn bộ domain
        response.addCookie(cookie);
    }

    // Xóa Cookie giỏ hàng (khi thanh toán)
    public static void clearCartCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(CART_COOKIE_NAME, "");
        cookie.setMaxAge(0); // Xóa ngay lập tức
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
