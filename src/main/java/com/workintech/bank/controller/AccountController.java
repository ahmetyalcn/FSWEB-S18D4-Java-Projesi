package com.workintech.bank.controller;

import com.workintech.bank.entity.Account;
import com.workintech.bank.entity.Address;
import com.workintech.bank.entity.Customer;
import com.workintech.bank.service.AccountService;
import com.workintech.bank.service.AddressService;
import com.workintech.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Account> findAll(){
        return accountService.findAll();
    }
    @GetMapping("/{id}")
    public Account find(@PathVariable int id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public Account save(@RequestBody Account account,@PathVariable int customerId){
        Customer customer = customerService.find(customerId);
        customer.add(account);
        account.setCustomer(customer);
        return accountService.save(account);
    }
    @PutMapping("/{customerId}")
    public Account update(@RequestBody Account account,@PathVariable int customerId){
        Customer customer = customerService.find(customerId);
        Account foundedAccount = customer.getAccountList()
                .stream().filter(a -> a.getId() == account.getId()).collect(Collectors.toList()).get(0);

        int index = customer.getAccountList().indexOf(foundedAccount);
        customer.getAccountList().set(index,foundedAccount);

        account.setCustomer(customer);
        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable int id){
        Account founded = find(id);
        accountService.delete(founded);
        return founded;
    }


}
