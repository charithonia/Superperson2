/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.*;
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
	    throws InvalidObjectException, DuplicateObjectException {
	Location loc = new Location();
	loc.setLatitude(0.9);
	loc.setLongitude(0.9);
	
	loc = locService.addLocation(loc);
	
	Location result = locService.getLocationById(loc.getId());
	
	assertNotNull(result);
    }
    
    @Test
    @Transactional
    public void testAddLocationWithAddress()
	    throws InvalidObjectException, DuplicateObjectException {
	Location loc = new Location();
	loc.setLatitude(0.9);
	loc.setLongitude(0.9);
	
	Address adr = new Address();
	adr.setNumber("123");
	adr.setStreet("Test St.");
	adr.setCity("Test City");
	adr.setState("OH");
	adr.setZip("12345");
	
	loc.setAddress(adr);
	
	loc = locService.addLocation(loc);
	
	Address result = loc.getAddress();
	
	assertNotNull(result);
	
	// Nonzero address means address successfully added
	assertTrue(result.getId() != 0);
    }
    
    @Test
    @Transactional
    public void testUpdateLocationAddress()
	    throws InvalidObjectException, DuplicateObjectException {
	Location loc = new Location();
	loc.setLatitude(0.9);
	loc.setLongitude(0.9);
	
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
	
	// Nonzero id means address successfully added
	assertTrue(resultAdr.getId() != 0);
    }
    
    @Test
    @Transactional
    public void testInvalidLocation()
	    throws DuplicateObjectException {
	Location loc1 = new Location();
	loc1.setLatitude(90.1);
	loc1.setLongitude(180.1);
	
	Location loc2 = new Location();
	loc2.setLatitude(-90.1);
	loc2.setLongitude(-180.1);
	
	boolean thrown = false;
	try {
	    locService.addLocation(loc1);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
	
	thrown = false;
	try {
	    locService.addLocation(loc2);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
    }
}
