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

import com.sg.superperson2.dao.PowerDao;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class PowerDaoTest {
    
    @Inject
    PowerDao powerDao;
    
    // 3 powers in database
    private final int N_POWERS = 3;
    
    @Test
    @Transactional
    public void testAddGetRemovePower() {
	Power pow1 = new Power();
	pow1.setName("X-Ray Vision");
	pow1.setDescription("This is some text.");
	
	Power pow2 = new Power();
	pow2.setName("Pyrokinesis");
	pow2.setDescription("This is some text.");
	
	powerDao.addPower(pow1);
	powerDao.addPower(pow2);
	
	Power result;
	List<Power> resultList;
	
	resultList = powerDao.getAllPowers();
	
	assertTrue(resultList.size() == N_POWERS + 2);
	
	result = powerDao.getPowerById(pow1.getId());
	
	assertNotNull(result);
	assertEquals(pow1.getName(), "X-Ray Vision");
	assertEquals(pow1.getDescription(), "This is some text.");
	
	result = powerDao.getPowerById(pow2.getId());
	
	assertNotNull(result);
	assertEquals(pow2.getName(), "Pyrokinesis");
	assertEquals(pow2.getDescription(), "This is some text.");
	
	powerDao.removePower(pow1);
	resultList = powerDao.getAllPowers();
	
	assertTrue(resultList.size() == N_POWERS + 1);
	
	powerDao.removePower(pow2);
	resultList = powerDao.getAllPowers();
	
	assertTrue(resultList.size() == N_POWERS);
    }
    
    @Test
    @Transactional
    public void testGetPowersBySuperperson() {
	Superperson sup = new Superperson();
	sup.setId(2);
	// This superperson should have 2 powers:
	// Teleportation (id 1), Telekinesis (id 3)
	
	List<Power> resultList = powerDao.getPowersBySuperperson(sup);
	
	assertFalse(resultList.isEmpty());
	
	boolean hasTeleportation = false;
	boolean hasTelekinesis = false;
	
	for (Power pow : resultList) {
	    if (pow.getId() == 1) {
		hasTeleportation = true;
	    }
	    else if (pow.getId() == 3) {
		hasTelekinesis = true;
	    }
	}
	
	assertTrue("Superperson should have Teleportation (power id 1)",
		hasTeleportation);
	assertTrue("Superperson should have Telekinesis (power id 3)",
		hasTelekinesis);
    }
}
