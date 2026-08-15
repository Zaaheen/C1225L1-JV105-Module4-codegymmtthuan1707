package com.customermanagement.controller;

import com.customermanagement.model.Customer;
import com.customermanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String index(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Added customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            redirect.addFlashAttribute("error", "Customer not found!");
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Updated customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            redirect.addFlashAttribute("error", "Customer not found!");
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            redirect.addFlashAttribute("error", "Customer not found!");
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "view";
    }
}