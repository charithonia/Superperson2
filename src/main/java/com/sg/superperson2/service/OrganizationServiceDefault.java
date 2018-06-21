/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.OrganizationDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Organization;

/**
 *
 * @author main
 */
public class OrganizationServiceDefault implements OrganizationService {
    
    @Inject
    OrganizationDao orgDao;
    
    @Override
    public Organization addOrganization(Organization org)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(org)) {
	    throw new InvalidObjectException("Organization invalid.");
	}
	if (isDuplicate(org)) {
	    throw new DuplicateObjectException("Organization is a duplicate.");
	}
	
	Location loc = org.getLocation();
	if (loc != null) {
	    
	}
	
	return orgDao.addOrganization(org);
    }
    
    @Override
    public void removeOrganization(Organization org) {
	orgDao.removeOrganization(org);
    }
    
    @Override
    public void updateOrganization(Organization org)
	    throws InvalidObjectException {
	if (!isValid(org)) {
	    throw new InvalidObjectException("Organization invalid.");
	}
	
	orgDao.updateOrganization(org);
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
	return orgDao.getAllOrganizations();
    }
    
    @Override
    public Organization getOrganizationById(int id) {
	return orgDao.getOrganizationById(id);
    }
    
    private boolean isValid(Organization org) {
	String strField;
	
	strField = org.getName();
	if (strField == null || strField.trim().equals("")) {
	    return false;
	}
	
	return true;
    }
    
    private boolean isDuplicate(Organization org) {
	List<Organization> allOrgs = getAllOrganizations();
	for (Organization currentOrg : allOrgs) {
	    if (isMatch(currentOrg, org)) {
		return true;
	    }
	}
	return false;
    }
    
    private boolean isMatch(Organization org1, Organization org2) {
	return org1.getName().equalsIgnoreCase(org2.getName());
    }
}
