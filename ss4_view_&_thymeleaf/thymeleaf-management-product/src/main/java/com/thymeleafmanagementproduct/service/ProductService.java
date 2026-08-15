package com.thymeleafmanagementproduct.service;

import com.thymeleafmanagementproduct.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private static final Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "Áo Sơ Mi Oxford Premium", 450000, "Chất liệu Cotton 100% thoáng mát, kiểu dáng Slim-fit lịch lãm", "Routine"));
        products.put(2, new Product(2, "Quần Jeans Denim Vintage", 680000, "Vải Denim co giãn nhẹ, nhuộm màu sắc tự nhiên không phai", "Uniqlo"));
        products.put(3, new Product(3, "Áo Khoác Blazer Hàn Quốc", 1250000, "Thiết kế 2 lớp cao cấp, phù hợp công sở và sự kiện", "Zara"));
        products.put(4, new Product(4, "Đầm Lụa Dáng Xoè Đi Tiệc", 890000, "Chất lụa tơ tằm mềm mịn, tôn dáng tôn da", "H&M"));
        products.put(5, new Product(5, "Áo Polo Nam Thể Thao", 320000, "Công nghệ thấm hút mồ hôi Coolmate, co giãn 4 chiều", "Owens"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public boolean save(Product product) {
        if (product.getId() == 0) {
            int autoId = products.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
            product.setId(autoId);
        }
        products.put(product.getId(), product);
        return true;
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public boolean update(int id, Product product) {
        if (products.containsKey(id)) {
            products.put(id, product);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        return products.remove(id) != null;
    }

    @Override
    public List<Product> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return findAll();
        }
        String keyword = name.trim().toLowerCase();
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
}
