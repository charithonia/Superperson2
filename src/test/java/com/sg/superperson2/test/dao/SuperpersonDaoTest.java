/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.SightingDao;
import com.sg.superperson2.dao.SuperpersonDao;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.Superperson;
import java.time.Month;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SuperpersonDaoTest {
    
    @Inject
    SightingDao sightingDao;
    
    @Inject
    SuperpersonDao superpersonDao;
    
    // db contents
    private final int N_SUPERPERSONS = 2;
    
    @Test
    @Transactional
    public void testAddGetRemoveSuperperson() {

	Superperson sup1 = new Superperson();
	sup1.setName("Batman");
	sup1.setRealName("Bruce Wayne");
	
	LocalDate birthday = LocalDate.parse("1940-01-01");
	sup1.setDateOfBirth(birthday);
	
	sup1.setDescription("The batman.");
	
	// add
	superpersonDao.addSuperperson(sup1);
	
	Superperson result;
	List<Superperson> resultList;
	
	// get
	resultList = superpersonDao.getAllSuperpersons();
	
	result = superpersonDao.getSuperpersonById(sup1.getId());
	
	assertNotNull(result);
	assertEquals(sup1.getName(), "Batman");
	assertEquals(sup1.getRealName(), "Bruce Wayne");
	assertEquals(sup1.getDateOfBirth(), birthday);
	assertEquals(sup1.getDescription(), "The batman.");

	assertTrue(resultList.size() == N_SUPERPERSONS + 1);
	
	// remove
	superpersonDao.removeSuperperson(sup1);
	resultList = superpersonDao.getAllSuperpersons();
	
	assertTrue(resultList.size() == N_SUPERPERSONS);
    }
    
    @Test
    @Transactional
    public void testGetSuperpersonsBySighting() {
	Sighting sig = sightingDao.getSightingById(2);
	
	// This sighting involves the following superpeople:
	// Captain Freeworld (id 1)
	// Teleporto (id 2)
	
	List<Superperson> resultList = superpersonDao.
		getSuperpersonsBySighting(sig);
	
	Superperson captain = superpersonDao.getSuperpersonById(1);
	Superperson teleporto = superpersonDao.getSuperpersonById(2);
	
	boolean hasCaptain = false;
	boolean hasTeleporto = false;
	
	for (Superperson sup : resultList) {
	    if (sup.equals(captain)) {
		hasCaptain = true;
	    }
	    if (sup.equals(teleporto)) {
		hasTeleporto = true;
	    }
	}
	
	assertTrue(hasCaptain);
	assertTrue(hasTeleporto);
    }
}
