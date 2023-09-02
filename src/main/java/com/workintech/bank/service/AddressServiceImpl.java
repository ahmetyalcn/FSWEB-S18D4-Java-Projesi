package com.workintech.bank.service;

import com.workintech.bank.dao.AddressRepository;
import com.workintech.bank.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    @Override
    public Address find(int id) {
        Optional<Address> founded = addressRepository.findById(id);
        if (founded.isPresent()){
            return founded.get();
        }
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
