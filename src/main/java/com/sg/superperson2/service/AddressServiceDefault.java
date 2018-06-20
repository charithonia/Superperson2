/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.AddressDao;
import com.sg.superperson2.exception.InvalidObjectException;
import com.sg.superperson2.model.Address;

/**
 *
 * @author main
 */
public class AddressServiceDefault implements AddressService {
    
    @Inject
    AddressDao addressDao;
    
    @Override
    public Address addAddress(Address address)
	    throws InvalidObjectException {
	
	if (isValid(address)) {
	    // If duplicate, return existing
	    List<Address> addresses = getAllAddresses();
	    for (Address currentAddress : addresses) {
		if (isDuplicate(currentAddress, address)) {
		    return currentAddress;
		}
	    }

	    return addressDao.addAddress(address);
	}
	else {
	    throw new InvalidObjectException("Invalid address!");
	}
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
    
    private boolean isDuplicate(Address address1, Address address2) {
	if (!address1.getNumber().equalsIgnoreCase(address2.getNumber())) {
	    return false;
	}
	if (!address1.getStreet().equalsIgnoreCase(address2.getStreet())) {
	    return false;
	}
	if (!address1.getCity().equalsIgnoreCase(address2.getCity())) {
	    return false;
	}
	if (!address1.getState().equalsIgnoreCase(address2.getState())) {
	    return false;
	}
	if (!address1.getZip().equalsIgnoreCase(address2.getZip())) {
	    return false;
	}
	return true;
    }
    
    private boolean isValid(Address address) {
	String field;

	field = address.getNumber();
	if (isBlank(field)) {
	    return false;
	}
	
	field = address.getStreet();
	if (isBlank(field)) {
	    return false;
	}
	
	field = address.getCity();
	if (isBlank(field)) {
	    return false;
	}
	
	field = address.getCity();
	if (isBlank(field)) {
	    return false;
	}
	
	field = address.getCity();
	if (isBlank(field)) {
	    return false;
	}
	
	field = address.getState();
	if (isBlank(field)) {
	    return false;
	}
	
	field = address.getZip();
	if (isBlank(field)) {
	    return false;
	}
	
	return true;
    }
    
    private boolean isBlank(String str) {
	return (str == null || str.trim().equals(""));
    }
}
