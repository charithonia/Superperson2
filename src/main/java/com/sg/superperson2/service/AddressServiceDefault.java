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
import com.sg.superperson2.model.AddressCommand;
import com.sg.superperson2.model.Location;

/**
 *
 * @author main
 */
public class AddressServiceDefault implements AddressService {
    
    @Inject
    AddressDao adrDao;
    
    @Inject
    LocationService locService;
    
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
	
	return adrDao.addAddress(address);
    }
    
    @Override
    public Address addAddress(AddressCommand adrCM)
	    throws InvalidObjectException {
	Address address = convertFromCommand(adrCM);
	return addAddress(address);
    }
    
    @Override
    public void removeAddress(Address address)
	    throws NotFoundException, DeleteLinkedObjectException {
	if (!exists(address)) {
	    throw new NotFoundException("Address not found.");
	}
	if (isLinked(address)) {
	    throw new DeleteLinkedObjectException("Address in use.");
	}
	adrDao.removeAddress(address);
    }
    
    @Override
    public void removeAddress(AddressCommand adrCM)
	    throws NotFoundException, DeleteLinkedObjectException {
	Address address = convertFromCommand(adrCM);
	removeAddress(address);
    }
    
    @Override
    public void updateAddress(Address address)
	    throws InvalidObjectException {
	if (!isValid(address)) {
	    throw new InvalidObjectException();
	}
	
	adrDao.updateAddress(address);
    }
    
    @Override
    public void updateAddress(AddressCommand adrCM)
	    throws InvalidObjectException {
	Address address = convertFromCommand(adrCM);
	updateAddress(address);
    }
    
    @Override
    public List<Address> getAllAddresses() {
	return adrDao.getAllAddresses();
    }
    
    @Override
    public Address getAddressById(int id) {
	return adrDao.getAddressById(id);
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
    
    private boolean exists(Address address) {
	Address result = getAddressById(address.getId());
	return !(result == null);
    }
    
    private boolean isLinked(Address address) {
	boolean isLinked = false;
	List<Location> locs = locService.getAllLocations();
	int adrId = address.getId();
	for (Location loc : locs) {
	    int locId = loc.getId();
	    if (adrId == locId) {
		isLinked = true;
	    }
	}
	return isLinked;
    }
    
    private AddressCommand convertToCommand(Address address) {
	AddressCommand adrCM = new AddressCommand();
	adrCM.setId(address.getId());
	adrCM.setNumber(address.getNumber());
	adrCM.setStreet(address.getStreet());
	adrCM.setCity(address.getCity());
	adrCM.setState(address.getState());
	adrCM.setZip(address.getZip());
	
	return adrCM;
    }
    
    private Address convertFromCommand(AddressCommand adrCM) {
	Address address = new Address();
	
	int id = adrCM.getId();
	if (id != 0) {
	    address.setId(id);
	}
	
	address.setNumber(adrCM.getNumber());
	address.setStreet(adrCM.getStreet());
	address.setCity(adrCM.getCity());
	address.setState(adrCM.getState());
	address.setZip(adrCM.getZip());
	
	return address;
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
