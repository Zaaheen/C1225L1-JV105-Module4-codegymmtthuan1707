package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("customers/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("/customers/detail")
    public ModelAndView showDetail(@RequestParam("id") int customerId) {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        Customer customer = customerService.findById(customerId);
        System.out.println(customer);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
}
