/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.AddressDao;
import com.sg.superperson2.dao.LocationDao;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class LocationDaoTest {
    
    @Inject
    AddressDao addressDao;
    
    @Inject
    LocationDao locationDao;
    
    // Test database contains 3 addresses, 3 locations
    
    @Test
    @Transactional
    public void testAddGetRemoveLocation() {
	
	// prerequisites
	//Address adr1 = new Address();
	//adr1.setId(1);
	
	// testing
	Location loc1 = new Location();
	loc1.setLatitude(45.0000);
	loc1.setLongitude(90.0000);
	loc1.setName("Starbucks");
	
	Location loc2 = new Location();
	loc2.setLatitude(-45.0000);
	loc2.setLongitude(180.0000);
	loc2.setName("County Courthouse");
	
	locationDao.addLocation(loc1);
	locationDao.addLocation(loc2);
	
	Location result;
	List<Location> resultList;
	
	resultList = locationDao.getAllLocations();
	
	assertTrue(resultList.size() == 5);
	
	result = locationDao.getLocationById(loc1.getId());
	
	assertNotNull(result);
	assertTrue(result.getLatitude() == 45.0000);
	assertTrue(result.getLongitude() == 90.0000);
	assertEquals(result.getName(), "Starbucks");
	
	result = locationDao.getLocationById(loc2.getId());
	assertNotNull(result);
	assertTrue(result.getLatitude() == -45.0000);
	assertTrue(result.getLongitude() == 180.0000);
	assertEquals(result.getName(), "County Courthouse");
	
	locationDao.removeLocation(loc1);
	resultList = locationDao.getAllLocations();
	
	assertTrue(resultList.size() == 4);
	
	locationDao.removeLocation(loc2);
	resultList = locationDao.getAllLocations();
	
	assertTrue(resultList.size() == 3);
    }
    
    @Test
    @Transactional
    public void testRemoveLocationAddress() {
	Location loc = new Location();
	locationDao.addLocation(loc);
	Address adr = loc.getAddress();
	
	locationDao.removeLocation(loc);
	Address result = addressDao.getAddressById(adr.getId());
	
	assertNull(result);
    }
}
