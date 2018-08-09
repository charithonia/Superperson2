/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.OrganizationCommand;
import com.sg.superperson2.model.OrganizationView;
import com.sg.superperson2.service.AddressService;
import com.sg.superperson2.service.LocationService;
import com.sg.superperson2.service.OrganizationService;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class OrganizationServiceTest {
    
    @Inject
    AddressService adrService;
    
    @Inject
    LocationService locService;
    
    @Inject
    OrganizationService orgService;
    
    @Test
    @Transactional
    public void testInvalidOrganization()
	    throws DuplicateObjectException {
	Organization org = new Organization();
	
	boolean thrown = false;
	try {
	    orgService.addOrganization(org);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testConvertOrganizationToView() {
	Address adr = new Address();
	adr.setNumber("123");
	adr.setStreet("Test St.");
	adr.setCity("Test City");
	adr.setState("NY");
	adr.setZip("12345");
	
	try {
	    adrService.addAddress(adr);
	}
	catch (InvalidObjectException ex) {
	    fail("testConvertOrganizationToView: unexpected exception: "
		    + ex.getMessage());
	}
	int adrId = adr.getId();
	
	Location loc = new Location();
	loc.setLatitude(0.123);
	loc.setLongitude(0.123);
	loc.setName("Test Location");
	loc.setAddress(adr);
	
	try {
	    locService.addLocation(loc);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("testConvertOrganizationToView: unexpected exception: "
		    + ex.getMessage());
	}
	int locId = loc.getId();
	
	Organization org = new Organization();
	org.setName("Test Organization");
	org.setHead("Test Head");
	org.setDescription("Description.");
	
	Location locStub = new Location();
	locStub.setId(locId);
	
	Address adrStub = new Address();
	adrStub.setId(adrId);
	locStub.setAddress(adrStub);
	
	org.setLocation(locStub);
	
	try {
	    orgService.addOrganization(org);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("testConvertOrganizationToView: unexpected exception: "
		    + ex.getMessage());
	}
	int orgId = org.getId();
	
	OrganizationView result = orgService.getOrganizationViewById(orgId);
	
	assertNotNull(result);
	assertEquals("Test Organization", result.getName());
	assertEquals("Test Head", result.getHead());
	assertEquals("Description.", result.getDescription());
	
	Location resultLoc = result.getLocation();
	
	assertNotNull(resultLoc);
	assertEquals("Test Location", resultLoc.getName());
	
	Address resultAdr = resultLoc.getAddress();
	
	assertNotNull(resultAdr);
	assertEquals("123", resultAdr.getNumber());
	assertEquals("Test St.", resultAdr.getStreet());
	assertEquals("Test City", resultAdr.getCity());
	assertEquals("NY", resultAdr.getState());
	assertEquals("12345", resultAdr.getZip());
    }
    
    @Test
    @Transactional
    public void testAddOrganizationNoLocation() {
	Organization org = new Organization();
	org.setName("Test Organization");
	
	try {
	    orgService.addOrganization(org);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("testNewOrganizationLocationNotNull: unexpected exception: "
		    + ex.getMessage());
	}
	int orgId = org.getId();
	
	Organization result = orgService.getOrganizationById(orgId);
	
	assertNotNull(result);
	assertNotNull(result.getLocation());
    }
    
    @Test
    @Transactional
    public void testUpdateOrganizationFromCommand() {
	
	// Org with no location
	Organization org = new Organization();
	org.setName("Name");
	org.setDescription("Description.");
	
	try {
	    orgService.addOrganization(org);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("testUpdateOrganizationFromCommand: unexpected exception: "
		    + ex.getMessage());
	}
	int orgId = org.getId();
	
	Location loc = new Location();
	loc.setLatitude(0.123);
	loc.setLongitude(0.123);
	loc.setName("Test Location");
	
	try {
	    locService.addLocation(loc);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("testUpdateOrganizationFromCommand: unexpected exception: "
		    + ex.getMessage());
	}
	int locId = loc.getId();
	
	OrganizationCommand orgCom = new OrganizationCommand();
	orgCom.setId(orgId);
	orgCom.setName("Name Updated");
	orgCom.setDescription("Description updated.");
	orgCom.setLocationId(locId);
	
	boolean thrownInvalid = false;
	boolean thrownDuplicate = false;
	try {
	    orgService.updateOrganization(orgCom);
	}
	catch (InvalidObjectException ex) {
	    thrownInvalid = true;
	}
	catch (DuplicateObjectException ex) {
	    thrownDuplicate = true;
	}
	
	assertFalse(thrownInvalid);
	assertFalse(thrownDuplicate);
	
	Organization result = orgService.getOrganizationById(orgId);
	
	assertNotNull(result);
	assertEquals("Name Updated", result.getName());
	assertEquals("Description updated.", result.getDescription());
	
	Location resultLoc = result.getLocation();
	assertTrue(resultLoc.getId() == loc.getId());
    }
}
