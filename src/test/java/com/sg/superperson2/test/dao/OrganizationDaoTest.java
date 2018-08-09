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

import com.sg.superperson2.dao.LocationDao;
import com.sg.superperson2.dao.OrganizationDao;
import com.sg.superperson2.dao.SuperpersonDao;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Superperson;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class OrganizationDaoTest {
    
    @Inject
    LocationDao locationDao;
    
    @Inject
    OrganizationDao organizationDao;
    
    @Inject
    SuperpersonDao superpersonDao;
    
    // Test database contains 3 organizations
    private final int N_ORGS = 3;
    
    @Test
    @Transactional
    public void testAddGetRemoveOrganization() {
	// prerequisites
	Location loc1 = new Location();
	loc1.setId(1);
	
	// testing
	Organization org1 = new Organization();
	org1.setName("CONTROL");
	org1.setHead("Chief");
	org1.setDescription("US secret agency.");
	org1.setLocation(loc1);
	
	Organization org2 = new Organization();
	org2.setName("KAOS");
	org2.setHead("Mr. Big");
	org2.setDescription("USSR secret agency.");
	org2.setLocation(loc1);
	
	organizationDao.addOrganization(org1);
	organizationDao.addOrganization(org2);
	
	Organization result;
	List<Organization> resultList;
	
	resultList = organizationDao.getAllOrganizations();
	
	assertTrue(resultList.size() == N_ORGS + 2);
	
	result = organizationDao.getOrganizationById(org1.getId());
	
	assertNotNull(result);
	assertEquals("CONTROL", result.getName());
	assertEquals("Chief", result.getHead());
	assertEquals("US secret agency.", result.getDescription());
	
	result = organizationDao.getOrganizationById(org2.getId());
	
	assertNotNull(result);
	assertEquals("KAOS", result.getName());
	assertEquals("Mr. Big", result.getHead());
	assertEquals("USSR secret agency.", result.getDescription());
	
	organizationDao.removeOrganization(org1);
	resultList = organizationDao.getAllOrganizations();
	
	assertTrue(resultList.size() == N_ORGS + 1);
	
	organizationDao.removeOrganization(org2);
	resultList = organizationDao.getAllOrganizations();
	
	assertTrue(resultList.size() == N_ORGS);
    }
    
    @Test
    @Transactional
    public void testGetOrganizationsBySuperperson() {
	Superperson sup = superpersonDao.getSuperpersonById(1);
	
	// This superperson belongs to:
	// Hero League (id 1)
	// Freemasons (id 3)
	
	List<Organization> resultList = organizationDao
		.getOrganizationsBySuperperson(sup);
	
	assertFalse(resultList.isEmpty());
	
	boolean inHeroLeague = false;
	boolean inFreemasons = false;
	
	for (Organization org : resultList) {
	    if (org.getId() == 1) {
		inHeroLeague = true;
	    }
	    if (org.getId() == 3) {
		inFreemasons = true;
	    }
	}
	
	assertTrue(inHeroLeague);
	assertTrue(inFreemasons);
    }
    
    @Test
    @Transactional
    public void testUpdateOrganization() {
	
    }
}
