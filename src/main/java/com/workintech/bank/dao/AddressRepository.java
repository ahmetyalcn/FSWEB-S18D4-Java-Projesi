package com.workintech.bank.dao;

import com.workintech.bank.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer>{
}
