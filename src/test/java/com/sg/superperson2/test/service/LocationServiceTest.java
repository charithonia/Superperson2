/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.InvalidObjectException;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.service.AddressService;
import com.sg.superperson2.service.LocationService;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class LocationServiceTest {
    
    @Inject
    AddressService adrService;
    
    @Inject
    LocationService locService;
    
    @Test
    @Transactional
    public void testAddLocationWithoutAddress()
	    throws InvalidObjectException {
	Location loc = new Location();
	loc.setLatitude(0.0);
	loc.setLongitude(0.0);
	
	loc = locService.addLocation(loc);
	
	Location result = locService.getLocationById(loc.getId());
	
	assertNotNull(result);
    }
    
    @Test
    @Transactional
    public void testAddLocationWithAddress()
	    throws InvalidObjectException {
	Location loc = new Location();
	loc.setLatitude(0.0);
	loc.setLongitude(0.0);
	
	Address adr = new Address();
	adr.setNumber("123");
	adr.setStreet("Test St.");
	adr.setCity("Test City");
	adr.setState("OH");
	adr.setZip("12345");
	
	loc.setAddress(adr);
	
	loc = locService.addLocation(loc);
	
	Location result = locService.getLocationById(loc.getId());
	
	assertNotNull(result);
	assertNotNull(result.getAddress());
    }
    
    @Test
    @Transactional
    public void testUpdateLocationAddress() throws InvalidObjectException {
	Location loc = new Location();
	loc.setLatitude(0.0);
	loc.setLongitude(0.0);
	
	loc = locService.addLocation(loc);
	
	Address adr = new Address();
	adr.setNumber("123");
	adr.setStreet("Test St.");
	adr.setCity("Test City");
	adr.setState("OH");
	adr.setZip("12345");
	
	loc.setAddress(adr);
	
	locService.updateLocation(loc);
	Location resultLoc = locService.getLocationById(loc.getId());
	
	assertNotNull(resultLoc.getAddress());
	
	int id = resultLoc.getAddress().getId();
	Address resultAdr = adrService.getAddressById(id);
	
	assertEquals("123", resultAdr.getNumber());
	assertEquals("Test St.", resultAdr.getStreet());
	assertEquals("Test City", resultAdr.getCity());
	assertEquals("OH", resultAdr.getState());
	assertEquals("12345", resultAdr.getZip());
    }
}
