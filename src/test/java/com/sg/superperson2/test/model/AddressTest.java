/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.sg.superperson2.model.Address;

/**
 *
 * @author main
 */
public class AddressTest {
    
    @Test
    public void testMatch() {
	Address adr1 = new Address();
	adr1.setNumber("123");
	adr1.setStreet("Example St.");
	adr1.setCity("Cleveland");
	adr1.setState("OH");
	adr1.setZip("12345");
	
	Address adr2 = new Address();
	adr2.setNumber("123");
	adr2.setStreet("example st.");
	adr2.setCity("cleveland");
	adr2.setState("oh");
	adr2.setZip("12345");
	
	Address adr3 = new Address();
	adr3.setNumber("1234");
	adr3.setStreet("example blvd.");
	adr3.setCity("New York");
	adr3.setState("NY");
	adr3.setZip("12345-6789");
	
	assertTrue(adr1.isMatch(adr2));
	
	assertFalse(adr1.isMatch(adr3));
    }
}
