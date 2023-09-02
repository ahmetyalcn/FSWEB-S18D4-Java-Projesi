package com.workintech.bank.controller;

import com.workintech.bank.entity.Address;
import com.workintech.bank.entity.Customer;
import com.workintech.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public Customer find(@PathVariable int id){
        return customerService.find(id);
    }

    @PostMapping("/")
    public Customer save(@RequestBody Customer customer) {
        System.out.println(customer.getAccountList());
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable int id) {
        Customer foundCustomer = customerService.find(id);
        if (foundCustomer != null) {
            customer.setId(id);
            return customerService.save(customer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable int id){
        Customer founded = find(id);
        customerService.delete(founded);
        return founded;
    }


}
