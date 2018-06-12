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
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.OrganizationDao;
import com.sg.superperson2.dao.SuperpersonOrganizationDao;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonOrganization;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SuperpersonOrganizationDaoTest {
    
    @Inject
    OrganizationDao orgDao;
    
    @Inject
    SuperpersonOrganizationDao supOrgDao;
    
    // 3 superperson_organizations in database
    private final int N_SUP_ORGS = 3;
    
    @Test
    @Transactional
    public void testAddGetRemoveOrganization() {
	
	// prerequisites
	Organization org = new Organization();
	org = orgDao.addOrganization(org);
	
	Superperson sup1 = new Superperson();
	sup1.setId(1);
	
	Superperson sup2 = new Superperson();
	sup2.setId(2);
	
	// testing
	SuperpersonOrganization supOrg1 = new SuperpersonOrganization();
	supOrg1.setOrganization(org);
	supOrg1.setSuperperson(sup1);
	
	SuperpersonOrganization supOrg2 = new SuperpersonOrganization();
	supOrg2.setOrganization(org);
	supOrg2.setSuperperson(sup2);
	
	supOrgDao.addSuperpersonOrganization(supOrg1);
	supOrgDao.addSuperpersonOrganization(supOrg2);
	
	List<SuperpersonOrganization> resultList = supOrgDao.
		getAllSuperpersonOrganizations();
	
	assertTrue(resultList.size() == N_SUP_ORGS + 2);
	
	SuperpersonOrganization result = supOrgDao.
		getSuperpersonOrganizationById(supOrg1.getId());
	
	assertNotNull(result);
	
	result = supOrgDao.
		getSuperpersonOrganizationById(supOrg2.getId());
	
	assertNotNull(result);
	
	supOrgDao.removeSuperpersonOrganization(supOrg1);
	
	resultList = supOrgDao.getAllSuperpersonOrganizations();
	
	assertTrue(resultList.size() == N_SUP_ORGS + 1);
	
	supOrgDao.removeSuperpersonOrganization(supOrg2);
	
	resultList = supOrgDao.getAllSuperpersonOrganizations();
	
	assertTrue(resultList.size() == N_SUP_ORGS);
    }
}
