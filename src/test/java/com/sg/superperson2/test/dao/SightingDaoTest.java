/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
import com.sg.superperson2.dao.SuperpersonDao;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.Superperson;
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
    SightingDao sigDao;
    
    @Inject
    SuperpersonDao supDao;
    
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
	
	sigDao.addSighting(sig1);
	sigDao.addSighting(sig2);
	
	Sighting result;
	List<Sighting> resultList;
	
	resultList = sigDao.getAllSightings();
	
	assertTrue(resultList.size() == N_SIGHTINGS + 2);
	
	result = sigDao.getSightingById(sig1.getId());
	
	assertNotNull(result);
	assertEquals(sig1.getTimestamp(), now);
	
	sigDao.removeSighting(sig1);
	resultList = sigDao.getAllSightings();
	
	assertTrue(resultList.size() == N_SIGHTINGS + 1);
	
	sigDao.removeSighting(sig2);
	resultList = sigDao.getAllSightings();
	
	assertTrue(resultList.size() == N_SIGHTINGS);
    }
    
    @Test
    @Transactional
    public void testSightingBridges() {
	LocalDateTime now = LocalDateTime.now();
	
	User usr = new User();
	usr.setId(1);
	
	Location loc = new Location();
	loc.setId(1);
	
	Superperson sup1 = new Superperson();
	sup1.setId(1);
	
	Superperson sup2 = new Superperson();
	sup2.setId(2);
	
	List<Superperson> sups = new ArrayList<>();
	sups.add(sup1);
	sups.add(sup2);
	
	Sighting sig = new Sighting();
	sig.setTimestamp(now);
	sig.setLocation(loc);
	sig.setUser(usr);
	sig.setSuperpersons(sups);
	
	sigDao.addSighting(sig);
	
	List<Superperson> supResults = supDao.getSuperpersonsBySighting(sig);
	boolean hasSup1 = false;
	boolean hasSup2 = false;
	for (Superperson currentSup : supResults) {
	    int id = currentSup.getId();
	    if (id == 1) {
		hasSup1 = true;
	    }
	    if (id == 2) {
		hasSup2 = true;
	    }
	}
	
	assertTrue(hasSup1);
	assertTrue(hasSup2);
    }

    @Test
    @Transactional
    public void testUpdateSightingSuperpeople() {
	LocalDateTime now = LocalDateTime.now();
	
	User usr = new User();
	usr.setId(1);
	
	Location loc = new Location();
	loc.setId(1);
	
	Superperson sup1 = new Superperson();
	sup1.setId(1);
	
	Superperson sup2 = new Superperson();
	sup2.setId(2);
	
	Superperson sup3 = new Superperson();
	sup3.setId(3);
	
	List<Superperson> oldSups = new ArrayList<>();
	oldSups.add(sup1);
	oldSups.add(sup2);
	
	List<Superperson> newSups = new ArrayList<>();
	newSups.add(sup2);
	newSups.add(sup3);
	
	Sighting sig = new Sighting();
	sig.setTimestamp(now);
	sig.setLocation(loc);
	sig.setUser(usr);
	sig.setSuperpersons(oldSups);
	
	sigDao.addSighting(sig);
	
	List<Superperson> supResults;
	boolean hasSup1, hasSup2, hasSup3;
	
	// Ensure sighting is linked to superpersons id 1 and 2
	supResults = supDao.getSuperpersonsBySighting(sig);
	hasSup1 = false;
	hasSup2 = false;
	for (Superperson currentSup : supResults) {
	    int id = currentSup.getId();
	    if (id == 1) {
		hasSup1 = true;
	    }
	    if (id == 2) {
		hasSup2 = true;
	    }
	}
	
	assertTrue(hasSup1);
	assertTrue(hasSup2);
	
	// Update sighting with a new superperson list
	sig.setSuperpersons(newSups);
	sigDao.updateSighting(sig);
	
	// Ensure sighting is linked to superpersons id 2 and 3 only
	supResults = supDao.getSuperpersonsBySighting(sig);
	hasSup1 = false;
	hasSup2 = false;
	hasSup3 = false;
	for (Superperson currentSup : supResults) {
	    int id = currentSup.getId();
	    if (id == 1) {
		hasSup1 = true;
	    }
	    if (id == 2) {
		hasSup2 = true;
	    }
	    if (id == 3) {
		hasSup3 = true;
	    }
	}
	
	assertFalse(hasSup1);
	assertTrue(hasSup2);
	assertTrue(hasSup3);
    }
}
