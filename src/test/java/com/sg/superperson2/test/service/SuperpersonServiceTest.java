/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonCommand;
import com.sg.superperson2.service.SuperpersonService;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SuperpersonServiceTest {
    
    @Inject
    SuperpersonService supService;
    
    @Test
    @Transactional
    public void testInvalidSuperperson()
	    throws DuplicateObjectException {
	Superperson sup = new Superperson();
	
	boolean thrown = false;
	try {
	    supService.addSuperperson(sup);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testAddSuperpersonFromCommand() {
	List<Integer> orgIds = new ArrayList<>();
	orgIds.add(1);
	orgIds.add(2);
	
	List<Integer> powIds = new ArrayList<>();
	powIds.add(1);
	powIds.add(2);
	
	// Name and description only
	SuperpersonCommand supCom1 = new SuperpersonCommand();
	supCom1.setName("Test 1");
	supCom1.setDescription("Description.");
	
	// Name, description, orgs, no powers
	SuperpersonCommand supCom2 = new SuperpersonCommand();
	supCom2.setName("Test 2");
	supCom2.setDescription("Description.");
	supCom2.setOrganizationIds(orgIds);
	
	// Name, description, orgs, powers
	SuperpersonCommand supCom3 = new SuperpersonCommand();
	supCom3.setName("Test 3");
	supCom3.setDescription("Description.");
	supCom3.setOrganizationIds(orgIds);
	supCom3.setPowerIds(powIds);
	
	Superperson result = null;
	boolean thrownInvalid = false;
	boolean thrownDuplicate = false;
	try {
	    result = supService.addSuperperson(supCom1);
	}
	catch (InvalidObjectException ex) {
	    thrownInvalid = true;
	}
	catch (DuplicateObjectException ex) {
	    thrownDuplicate = true;
	}
	
	assertFalse(thrownInvalid);
	assertFalse(thrownDuplicate);
	assertNotNull(result);
	assertEquals("Test 1", result.getName());
	assertEquals("Description.", result.getDescription());
	assertTrue(result.getOrganizations().isEmpty());
	assertTrue(result.getPowers().isEmpty());
	
	result = null;
	thrownInvalid = false;
	thrownDuplicate = false;
	try {
	    result = supService.addSuperperson(supCom2);
	}
	catch (InvalidObjectException ex) {
	    thrownInvalid = true;
	}
	catch (DuplicateObjectException ex) {
	    thrownDuplicate = true;
	}
	
	assertFalse(thrownInvalid);
	assertFalse(thrownDuplicate);
	assertNotNull(result);
	assertEquals("Test 2", result.getName());
	assertEquals("Description.", result.getDescription());
	assertFalse(result.getOrganizations().isEmpty());
	assertTrue(result.getPowers().isEmpty());
	
	result = null;
	thrownInvalid = false;
	thrownDuplicate = false;
	try {
	    result = supService.addSuperperson(supCom3);
	}
	catch (InvalidObjectException ex) {
	    thrownInvalid = true;
	}
	catch (DuplicateObjectException ex) {
	    thrownDuplicate = true;
	}
	
	assertFalse(thrownInvalid);
	assertFalse(thrownDuplicate);
	assertNotNull(result);
	assertEquals("Test 3", result.getName());
	assertEquals("Description.", result.getDescription());
	assertFalse(result.getOrganizations().isEmpty());
	assertFalse(result.getPowers().isEmpty());
    }
}
