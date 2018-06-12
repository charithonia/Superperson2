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
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.LocationDao;
import com.sg.superperson2.dao.SightingDao;
import com.sg.superperson2.dao.SightingSuperpersonDao;
import com.sg.superperson2.dao.SuperpersonDao;
import com.sg.superperson2.dao.UserDao;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingSuperperson;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SightingSuperpersonDaoTest {
    
    @Inject
    SightingDao sightingDao;
    
    @Inject
    SightingSuperpersonDao sightingSuperpersonDao;
    
    // 3 sighting_superpersons in database
    private final int N_SIGHTING_SUPERPERSONS = 3;
    
    @Test
    @Transactional
    public void testAddGetRemoveSightingSuperperson() {
	
	// prerequisites
	Superperson sup1 = new Superperson();
	sup1.setId(1);
	
	Superperson sup2 = new Superperson();
	sup2.setId(2);
	
	Location loc = new Location();
	loc.setId(1);
	
	User usr = new User();
	usr.setId(1);
	
	LocalDateTime now = LocalDateTime.now();
	
	Sighting sig = new Sighting();
	sig.setTimestamp(now);
	sig.setLocation(loc);
	sig.setUser(usr);
	
	sightingDao.addSighting(sig);
	
	SightingSuperperson sigSup1 = new SightingSuperperson();
	sigSup1.setSighting(sig);
	sigSup1.setSuperperson(sup1);
	
	SightingSuperperson sigSup2 = new SightingSuperperson();
	sigSup2.setSighting(sig);
	sigSup2.setSuperperson(sup2);
	
	sightingSuperpersonDao.addSightingSuperperson(sigSup1);
	sightingSuperpersonDao.addSightingSuperperson(sigSup2);
	
	List<SightingSuperperson> resultList = sightingSuperpersonDao.
		getAllSightingSuperpersons();
	
	assertTrue(resultList.size() == N_SIGHTING_SUPERPERSONS + 2);
	
	sightingSuperpersonDao.removeSightingSuperperson(sigSup1);
	
	resultList = sightingSuperpersonDao.getAllSightingSuperpersons();
	
	assertTrue(resultList.size() == N_SIGHTING_SUPERPERSONS + 1);
	
	sightingSuperpersonDao.removeSightingSuperperson(sigSup2);
	
	resultList = sightingSuperpersonDao.getAllSightingSuperpersons();
	
	assertTrue(resultList.size() == N_SIGHTING_SUPERPERSONS);
    }
}
