/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.time.LocalDateTime;
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

import com.sg.superperson2.dao.SightingDao;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SightingDaoTest {
    
    @Inject
    SightingDao sightingDao;
    
    // 2 sightings in database
    private final int N_SIGHTINGS = 2;
    
    @Test
    @Transactional
    public void testAddGetRemoveSighting() {
	LocalDateTime now = LocalDateTime.now();
	
	User usr = new User();
	usr.setId(1);
	
	Location loc = new Location();
	loc.setId(1);
	
	Sighting sig1 = new Sighting();
	sig1.setUser(usr);
	sig1.setLocation(loc);
	sig1.setTimestamp(now);
	
	Sighting sig2 = new Sighting();
	sig2.setUser(usr);
	sig2.setLocation(loc);
	sig2.setTimestamp(now);
	
	sightingDao.addSighting(sig1);
	sightingDao.addSighting(sig2);
	
	Sighting result;
	List<Sighting> resultList;
	
	resultList = sightingDao.getAllSightings();
	
	assertTrue(resultList.size() == N_SIGHTINGS + 2);
	
	result = sightingDao.getSightingById(sig1.getId());
	
	assertNotNull(result);
	assertEquals(sig1.getTimestamp(), now);
	
	sightingDao.removeSighting(sig1);
	resultList = sightingDao.getAllSightings();
	
	assertTrue(resultList.size() == N_SIGHTINGS + 1);
	
	sightingDao.removeSighting(sig2);
	resultList = sightingDao.getAllSightings();
	
	assertTrue(resultList.size() == N_SIGHTINGS);
    }
}
