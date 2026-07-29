package com.example.demo.service;

import com.example.demo.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(int id);
}
