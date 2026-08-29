package com.shop.service;

import com.shop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);
}
