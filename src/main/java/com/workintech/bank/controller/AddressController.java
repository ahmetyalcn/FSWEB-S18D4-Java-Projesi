package com.workintech.bank.controller;

import com.workintech.bank.entity.Address;
import com.workintech.bank.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public List<Address> findAll(){
        return addressService.findAll();
    }
    @GetMapping("/{id}")
    public Address find(@PathVariable int id){
        return addressService.find(id);
    }

    @PostMapping("/")
    public Address save(@RequestBody Address address){
        return addressService.save(address);
    }
    @PutMapping("/{id}")
    public Address update(@RequestBody Address address,@PathVariable int id){
        Address founded = find(id);
        if(founded != null){
            address.setId(id);
            return addressService.save(address);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Address delete(@PathVariable int id){
        Address founded = find(id);
        addressService.delete(founded);
        return founded;
    }


}
