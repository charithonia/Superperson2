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
import static org.junit.Assert.assertNotNull;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.PowerDao;
import com.sg.superperson2.dao.SuperpersonPowerDao;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonPower;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SuperpersonPowerTest {
    
    @Inject
    PowerDao powerDao;
    
    @Inject
    SuperpersonPowerDao supPowDao;
    
    private final int N_SUP_POWS = 3;
    
    @Test
    @Transactional
    public void testAddGetRemoveSuperpersonPower() {
	
	// prerequisites
	Power pow = new Power();
	pow = powerDao.addPower(pow);
	
	Superperson sup1 = new Superperson();
	sup1.setId(1);
	
	Superperson sup2 = new Superperson();
	sup2.setId(2);
	
	// testing
	SuperpersonPower supPow1 = new SuperpersonPower();
	supPow1.setPower(pow);
	supPow1.setSuperperson(sup1);
	
	SuperpersonPower supPow2 = new SuperpersonPower();
	supPow2.setPower(pow);
	supPow2.setSuperperson(sup2);
	
	supPowDao.addSuperpersonPower(supPow1);
	supPowDao.addSuperpersonPower(supPow2);
	
	SuperpersonPower result = supPowDao.
		getSuperpersonPowerById(supPow1.getId());
	
	assertNotNull(result);
	
	result = supPowDao.
		getSuperpersonPowerById(supPow2.getId());
	
	assertNotNull(result);
	
	List<SuperpersonPower> resultList = supPowDao.getAllSuperpersonPowers();
	
	assertTrue(resultList.size() == N_SUP_POWS + 2);
	
	supPowDao.removeSuperpersonPower(supPow1);
	
	resultList = supPowDao.getAllSuperpersonPowers();
	
	assertTrue(resultList.size() == N_SUP_POWS + 1);
	
	supPowDao.removeSuperpersonPower(supPow2);
	
	resultList = supPowDao.getAllSuperpersonPowers();
	
	assertTrue(resultList.size() == N_SUP_POWS);
    }
}
