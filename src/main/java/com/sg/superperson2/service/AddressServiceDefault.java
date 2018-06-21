/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.AddressDao;
import com.sg.superperson2.exception.*;
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
	
	if (!isValid(address)) {
	    throw new InvalidObjectException();
	}
	if (isDuplicate(address)) {
	    
	    // Use original instead of duplicate
	    return getOriginal(address);
	}
	
	return addressDao.addAddress(address);
    }
    
    @Override
    public void removeAddress(Address address) {
	addressDao.removeAddress(address);
    }
    
    @Override
    public void updateAddress(Address address)
	    throws InvalidObjectException {
	if (!isValid(address)) {
	    throw new InvalidObjectException();
	}
	
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
    
    private void validate(Address address)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(address)) {
	    throw new InvalidObjectException("Address invalid.");
	}
	if (isDuplicate(address)) {
	    throw new DuplicateObjectException("Address is a duplicate.");
	}
    }
    
    private boolean isValid(Address address) {
	List<String> fields = new ArrayList<>();
	fields.add(address.getNumber());
	fields.add(address.getStreet());
	fields.add(address.getCity());
	fields.add(address.getState());
	fields.add(address.getZip());
	
	for (String field : fields) {
	    if (field == null || field.trim().equals("")) {
		return false;
	    }
	}
	
	return true;
    }
    
    private boolean isDuplicate(Address address) {
	List<Address> allAdrs = getAllAddresses();
	
	for (Address currentAdr : allAdrs) {
	    if (isMatch(currentAdr, address)) {
		return true;
	    }
	}
	return false;
    }
    
    private boolean isMatch(Address adr1, Address adr2) {
	boolean matches = true;
	
	if (!adr1.getNumber()
		    .equalsIgnoreCase(adr2.getNumber())) {
		matches = false;
	}
	else if (!adr1.getStreet()
		.equalsIgnoreCase(adr2.getStreet())) {
	    matches = false;
	}
	else if (!adr1.getCity()
		.equalsIgnoreCase(adr2.getCity())) {
	    matches = false;
	}
	else if (!adr1.getState()
		.equalsIgnoreCase(adr2.getState())) {
	    matches = false;
	}
	else if (!adr1.getZip()
		.equalsIgnoreCase(adr2.getZip())) {
	    matches = false;
	}

	return (matches);
    }
    
    /**
       This method uses a duplicate entry to return the original.
       @param address The duplicate object
       @return The original object from database, null if no match
    */
    private Address getOriginal(Address address) {
	List<Address> allAdrs = getAllAddresses();
	for (Address currentAdr : allAdrs) {
	    if (isMatch(currentAdr, address)) {
		return currentAdr;
	    }
	}
	return null;
    }
}
