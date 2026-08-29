package com.shop.session;

import com.shop.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {}

    public Map<Product, Integer> getProducts() {
        return products;
    }

    private boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return entry;
            }
        }
        return null;
    }

    // Thêm sản phẩm vào giỏ
    public void addProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.put(itemEntry.getKey(), newQuantity);
        }
    }

    // Cập nhật số lượng sản phẩm
    public void updateQuantity(Product product, Integer quantity) {
        if (quantity <= 0) {
            removeProduct(product);
        } else {
            // Đã có equals() & hashCode() trong Product.java nên put sẽ tự động
            // thêm mới nếu chưa có, hoặc đè số lượng mới nếu đã tồn tại.
            products.put(product, quantity);
        }
    }

    // Xóa sản phẩm khỏi giỏ
    public void removeProduct(Product product) {
        Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
        if (itemEntry != null) {
            products.remove(itemEntry.getKey());
        }
    }

    // Tính tổng số lượng sản phẩm trong giỏ
    public Integer countProductQuantity() {
        Integer total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    // Tính tổng tiền đơn hàng
    public Double countTotalAmount() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getNewPrice() * entry.getValue();
        }
        return total;
    }

    // Làm trống giỏ hàng khi thanh toán
    public void clearCart() {
        products.clear();
    }

    public String toCookieString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (sb.length() > 0) {
                sb.append("_");
            }
            sb.append(entry.getKey().getId()).append("-").append(entry.getValue());
        }
        return sb.toString();
    }
}
