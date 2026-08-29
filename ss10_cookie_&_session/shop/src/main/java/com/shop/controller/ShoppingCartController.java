package com.shop.controller;


import com.shop.entity.Product;
import com.shop.service.IProductService;
import com.shop.session.Cart;
import com.shop.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ShoppingCartController {

    @Autowired
    private IProductService productService;

    // Khởi tạo đối tượng 'cart' và lưu vào Session nếu chưa tồn tại
    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    // 1. Danh sách sản phẩm
    @GetMapping({"/", "/shop"})
    public String showShop(Model model) {
        model.addAttribute("products", productService.findAll());
        return "shop";
    }

    // 2. Trang chi tiết sản phẩm
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "detail";
        }
        return "redirect:/shop";
    }

    // 3. Xem giỏ hàng
    @GetMapping("/shopping-cart")
    public String showCart(@ModelAttribute("cart") Cart cart,
                           HttpServletRequest request,
                           Model model) {
        restoreCartFromCookie(cart, request);
        model.addAttribute("cart", cart);
        return "cart";
    }

    // 4. Thêm sản phẩm vào giỏ hàng
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute("cart") Cart cart,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            cart.addProduct(productOptional.get());
            CookieUtils.saveCartToCookie(cart.toCookieString(), response);
        }
        return "redirect:/shopping-cart";
    }

    // 5. Cập nhật số lượng sản phẩm từ dropdown
    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam Long id,
                                 @RequestParam Integer quantity,
                                 @ModelAttribute("cart") Cart cart,
                                 HttpServletResponse response) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            cart.updateQuantity(productOptional.get(), quantity);
            CookieUtils.saveCartToCookie(cart.toCookieString(), response);
        }
        return "redirect:/shopping-cart";
    }

    // 6. Xóa sản phẩm khỏi giỏ hàng
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id,
                                 @ModelAttribute("cart") Cart cart,
                                 HttpServletResponse response) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            cart.removeProduct(productOptional.get());
            CookieUtils.saveCartToCookie(cart.toCookieString(), response);
        }
        return "redirect:/shopping-cart";
    }

    // 7. Hoàn tất thanh toán
    @PostMapping("/cart/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart,
                           RedirectAttributes redirect,
                           HttpServletResponse response) {
        if (cart.getProducts().isEmpty()) {
            redirect.addFlashAttribute("error", "Giỏ hàng của bạn đang trống!");
            return "redirect:/shopping-cart";
        }
        cart.clearCart();
        CookieUtils.saveCartToCookie(cart.toCookieString(), response);
        redirect.addFlashAttribute("message", "Thanh toán đơn hàng thành công! Cảm ơn bạn đã mua sắm.");
        return "redirect:/shop";
    }

    // Tự động khôi phục giỏ hàng từ Cookie nếu Session bị mất (như khi Restart Server)
    private void restoreCartFromCookie(Cart cart, HttpServletRequest request) {
        if (cart.getProducts().isEmpty()) {
            String cookieData = CookieUtils.getCartFromCookie(request);
            if (!cookieData.isEmpty()) {
                // Tách các item dạng "1-2_4-1"
                String[] items = cookieData.split("_");
                for (String item : items) {
                    String[] parts = item.split("-");
                    if (parts.length == 2) {
                        Long productId = Long.parseLong(parts[0]);
                        Integer quantity = Integer.parseInt(parts[1]);

                        Optional<Product> productOptional = productService.findById(productId);
                        if (productOptional.isPresent()) {
                            cart.updateQuantity(productOptional.get(), quantity);
                        }
                    }
                }
            }
        }
    }
}
