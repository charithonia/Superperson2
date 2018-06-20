/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.InvalidObjectException;
import com.sg.superperson2.model.Address;

/**
 *
 * @author main
 */
public interface AddressService {
    
    public Address addAddress(Address address)
	    throws InvalidObjectException;
    
    public void removeAddress(Address address);
    
    public void updateAddress(Address address);
    
    public List<Address> getAllAddresses();
    
    public Address getAddressById(int id);
}
