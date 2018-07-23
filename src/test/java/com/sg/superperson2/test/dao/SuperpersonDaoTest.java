/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.sg.superperson2.dao.SuperpersonOrganizationDao;
import com.sg.superperson2.dao.SuperpersonPowerDao;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonOrganization;
import com.sg.superperson2.model.SuperpersonPower;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SuperpersonDaoTest {
    
    @Inject
    SightingDao sigDao;
    
    @Inject
    SuperpersonDao supDao;
    
    @Inject
    SuperpersonOrganizationDao supOrgDao;
    
    @Inject
    SuperpersonPowerDao supPowDao;
    
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
	supDao.addSuperperson(sup1);
	
	Superperson result;
	List<Superperson> resultList;
	
	// get
	resultList = supDao.getAllSuperpersons();
	
	result = supDao.getSuperpersonById(sup1.getId());
	
	assertNotNull(result);
	assertEquals(sup1.getName(), "Batman");
	assertEquals(sup1.getRealName(), "Bruce Wayne");
	assertEquals(sup1.getDateOfBirth(), birthday);
	assertEquals(sup1.getDescription(), "The batman.");

	assertTrue(resultList.size() == N_SUPERPERSONS + 1);
	
	// remove
	supDao.removeSuperperson(sup1);
	resultList = supDao.getAllSuperpersons();
	
	assertTrue(resultList.size() == N_SUPERPERSONS);
    }
    
    @Test
    @Transactional
    public void testSightingHasSuperpersons() {
	
	Sighting sig = sigDao.getSightingById(2);
	
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
    
    @Test
    @Transactional
    public void testSuperpersonBridges() {
	Superperson sup = new Superperson();
	sup.setName("Test");
	
	Organization org1 = new Organization();
	org1.setId(1);
	
	Organization org2 = new Organization();
	org2.setId(2);
	
	List<Organization> orgs = new ArrayList<>();
	orgs.add(org1);
	orgs.add(org2);
	
	Power pow1 = new Power();
	pow1.setId(1);
	
	Power pow2 = new Power();
	pow2.setId(2);
	
	List<Power> pows = new ArrayList<>();
	pows.add(pow1);
	pows.add(pow2);
	
	sup.setOrganizations(orgs);
	sup.setPowers(pows);
	
	supDao.addSuperperson(sup);
	
	// Ensure that this superperson has correct bridges
	List<SuperpersonPower> supPowResults = supPowDao
		.getSuperpersonPowersBySuperperson(sup);
	
	boolean hasPow1 = false;
	boolean hasPow2 = false;
	for (SuperpersonPower currentSupPow : supPowResults) {
	    int id = currentSupPow.getPower().getId();
	    if (id == 1) {
		hasPow1 = true;
	    }
	    else if (id == 2) {
		hasPow2 = true;
	    }
	}
	
	assertTrue(hasPow1);
	assertTrue(hasPow2);
	
	List<SuperpersonOrganization> supOrgResults = supOrgDao
		.getSuperpersonOrganizationsBySuperperson(sup);
	
	boolean hasOrg1 = false;
	boolean hasOrg2 = false;
	for (SuperpersonOrganization currentSupOrg : supOrgResults) {
	    int id = currentSupOrg.getOrganization().getId();
	    if (id == 1) {
		hasOrg1 = true;
	    }
	    else if (id == 2) {
		hasOrg2 = true;
	    }
	}
	
	assertTrue(hasOrg1);
	assertTrue(hasOrg2);
    }
}
