/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

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
    private final int N_SUPERPERSONS = 3;
    
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
    public void testSightingHasSuperpersons() {
	
	Sighting sig = sightingDao.getSightingById(2);
	
	// This sighting involves the following superpeople:
	// Captain Freeworld (id 1)
	// Teleporto (id 2)
	
	List<Superperson> resultList = sig.getSuperpersons();
	boolean hasCaptain = false;
	boolean hasTeleporto = false;
	for (Superperson currentSup : resultList) {
	    int id = currentSup.getId();
	    if (id == 1) {
		hasCaptain = true;
	    }
	    if (id == 2) {
		hasTeleporto = true;
	    }
	}
	
	assertTrue(hasCaptain);
	assertTrue(hasTeleporto);
    }
}
