package com.thymeleafmanagementproduct.service;

import com.thymeleafmanagementproduct.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    boolean save(Product product);
    Product findById(int id);
    boolean update(int id, Product product);
    boolean remove(int id);
    List<Product> searchByName(String name);
}
