/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.PowerCommand;
import com.sg.superperson2.service.PowerService;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class PowerServiceTest {
    
    @Inject
    PowerService powService;
    
    @Test
    @Transactional
    public void testRemovePowerById() {
	Power pow = new Power();
	pow.setName("Test Power");
	pow.setDescription("created by PowerServiceTest");
	
	try {
	    powService.addPower(pow);
	}
	catch (InvalidObjectException
		| DuplicateObjectException ex) {
	    fail("PowerServiceTest: Unexpected exception: " + ex.getMessage());
	}
	
	int id = pow.getId();
	powService.removePowerById(id);
	
	Power result = powService.getPowerById(id);
	
	assertTrue(result == null);
    }
    
    @Test
    @Transactional
    public void testInvalidPower()
	    throws DuplicateObjectException {
	Power pow = new Power();
	
	boolean thrown = false;
	try {
	    powService.addPower(pow);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testInvalidPowerCommand() {
	PowerCommand powCom = new PowerCommand();
	powCom.setName("");
	powCom.setDescription("This power is unnamed, so it is invalid!");
	
	boolean thrown = false;
	boolean thrownWrong = false;
	try {
	    powService.addPower(powCom);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	catch (DuplicateObjectException ex) {
	    thrownWrong = true;
	}
	
	assertFalse(thrownWrong);
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testDuplicatePowerCommand() {
	PowerCommand powCom1 = new PowerCommand();
	powCom1.setName("Test Power");
	powCom1.setDescription("Description 1");
	
	PowerCommand powCom2 = new PowerCommand();
	powCom2.setName("Test Power");
	powCom2.setDescription("Description 2");
	
	// Add first
	try {
	    powService.addPower(powCom1);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("PowerServiceTest: Unexpected exception: " + ex.getMessage());
	}
	
	// Add duplicate
	boolean thrown = false;
	try {
	    powService.addPower(powCom2);
	}
	catch (DuplicateObjectException ex) {
	    thrown = true;
	}
	catch (InvalidObjectException ex) {
	    fail("PowerServiceTest: Unexpected exception: " + ex.getMessage());
	}
	
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testConvertPowerFromCommand() {
	PowerCommand powCom1 = new PowerCommand();
	powCom1.setName("Test");
	powCom1.setDescription("Description.");
	
	PowerCommand powCom2 = new PowerCommand();
	powCom2.setName("Test Power 2");
	powCom2.setDescription("Longer description.");
	
	Power result = powService.convertFromCommand(powCom1);
	
	assertEquals("Test", result.getName());
	assertEquals("Description.", result.getDescription());
	
	result = powService.convertFromCommand(powCom2);
	
	assertEquals("Test Power 2", result.getName());
	assertEquals("Longer description.", result.getDescription());
    }
    
    @Test
    @Transactional
    public void testConvertPowerToCommand() {
	Power pow1 = new Power();
	pow1.setName("Test");
	pow1.setDescription("Description.");
	
	Power pow2 = new Power();
	pow2.setName("Test Power 2");
	pow2.setDescription("Longer description.");
	
	PowerCommand result = powService.convertToCommand(pow1);
	
	assertEquals("Test", result.getName());
	assertEquals("Description.", result.getDescription());
	
	result = powService.convertToCommand(pow2);
	
	assertEquals("Test Power 2", result.getName());
	assertEquals("Longer description.", result.getDescription());
    }
    @Test
    @Transactional
    public void testAddPowerFromCommand() {
	Power result = null;
	
	PowerCommand powCom = new PowerCommand();
	powCom.setName("Test");
	powCom.setDescription("Description.");
	
	try {
	    result = powService.addPower(powCom);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("PowerServiceTest: Unexpected exception: " + ex.getMessage());
	}
	
	assertNotNull(result);
	assertEquals("Test", result.getName());
	assertEquals("Description.", result.getDescription());
    }
    
    @Test
    @Transactional
    public void testUpdatePowerFromCommand() {
	Power pow = new Power();
	pow.setName("Test");
	pow.setDescription("Description.");
	
	int powId = 0;
	try {
	    powService.addPower(pow);
	    powId = pow.getId();
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    fail("testUpdatePowerFromCommand: unexpected exception: "
		    + ex.getMessage());
	}
	
	PowerCommand powCom = new PowerCommand();
	powCom.setId(powId);
	powCom.setName("Test 2");
	powCom.setDescription("Different description.");
	
	boolean thrownInvalid = false;
	boolean thrownDuplicate = false;
	try {
	    powService.updatePower(powCom);
	}
	catch (InvalidObjectException ex) {
	    thrownInvalid = true;
	}
	catch (DuplicateObjectException ex) {
	    thrownDuplicate = true;
	}
	
	assertFalse(thrownInvalid);
	assertFalse(thrownDuplicate);
	
	Power result = powService.getPowerById(powId);
	
	assertEquals("Test 2", result.getName());
	assertEquals("Different description.", result.getDescription());
    }
}
