/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.AddressDao;
import com.sg.superperson2.model.Address;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class AddressDaoTest {
    
    @Inject
    AddressDao addressDao;
    
    // Test database already contains 3 addresses
    
    @Test
    @Transactional
    public void testAddGetRemoveAddress() {
	Address adr1 = new Address();
	adr1.setNumber("123");
	adr1.setStreet("Street St");
	adr1.setCity("City City");
	adr1.setState("AZ");
	adr1.setZip("01234");
	
	Address adr2 = new Address();
	adr2.setNumber("1234");
	adr2.setStreet("Avenue Ave");
	adr2.setCity("Cityville");
	adr2.setState("OH");
	adr2.setZip("01234-0123");
	
	addressDao.addAddress(adr1);
	addressDao.addAddress(adr2);
	
	Address result;
	List<Address> resultList;
	
	resultList = addressDao.getAllAddresses();
	
	assertTrue(resultList.size() == 5);
	
	result = addressDao.getAddressById(adr1.getId());
	
	assertNotNull(result);
	assertEquals(adr1.getNumber(), "123");
	assertEquals(adr1.getStreet(), "Street St");
	assertEquals(adr1.getCity(), "City City");
	assertEquals(adr1.getState(), "AZ");
	assertEquals(adr1.getZip(), "01234");
	
	result = addressDao.getAddressById(adr2.getId());
	
	assertNotNull(result);
	assertEquals(adr2.getNumber(), "1234");
	assertEquals(adr2.getStreet(), "Avenue Ave");
	assertEquals(adr2.getCity(), "Cityville");
	assertEquals(adr2.getState(), "OH");
	assertEquals(adr2.getZip(), "01234-0123");
	
	addressDao.removeAddress(adr1);
	resultList = addressDao.getAllAddresses();
	
	assertTrue(resultList.size() == 4);
	
	addressDao.removeAddress(adr2);
	resultList = addressDao.getAllAddresses();
	
	assertTrue(resultList.size() == 3);
    }
    
    @Test
    @Transactional
    public void testAddressEquals() {
	
	Address adr1;
	Address adr2;
	
	// test equal
	adr1 = addressDao.getAddressById(1);
	adr2 = addressDao.getAddressById(1);
	
	assertTrue(adr1.equals(adr2));
	
	// test not equal
	adr2 = addressDao.getAddressById(2);
	
	assertFalse(adr1.equals(adr2));
    }
}
