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
}
