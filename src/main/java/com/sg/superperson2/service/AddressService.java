/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.AddressCommandModel;

/**
 *
 * @author main
 */
public interface AddressService {
    
    public Address addAddress(Address address)
	    throws InvalidObjectException;
    
    public Address addAddress(AddressCommandModel adrCM)
	    throws InvalidObjectException;
    
    public void removeAddress(Address address)
	    throws NotFoundException, DeleteLinkedObjectException;
    
    public void removeAddress(AddressCommandModel adrCM)
	    throws NotFoundException, DeleteLinkedObjectException;
    
    public void updateAddress(Address address)
	    throws InvalidObjectException;
    
    public void updateAddress(AddressCommandModel adrCM)
	    throws InvalidObjectException;
    
    public List<Address> getAllAddresses();
    
    public Address getAddressById(int id);
}
