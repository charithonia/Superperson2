/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.AddressDao;
import com.sg.superperson2.model.Address;

/**
 *
 * @author main
 */
public class AddressServiceDefault implements AddressService {
    
    @Inject
    AddressDao addressDao;
    
    @Override
    public Address addAddress(Address address) {
	return addressDao.addAddress(address);
    }
    
    @Override
    public void removeAddress(Address address) {
	addressDao.removeAddress(address);
    }
    
    @Override
    public void updateAddress(Address address) {
	addressDao.updateAddress(address);
    }
    
    @Override
    public List<Address> getAllAddresses() {
	return addressDao.getAllAddresses();
    }
    
    @Override
    public Address getAddressById(int id) {
	return addressDao.getAddressById(id);
    }
}
