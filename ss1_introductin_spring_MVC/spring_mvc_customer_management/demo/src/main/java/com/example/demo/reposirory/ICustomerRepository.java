package com.example.demo.reposirory;

import com.example.demo.entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    Customer findById(int id);
}
