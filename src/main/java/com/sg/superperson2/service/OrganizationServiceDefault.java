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
import com.sg.superperson2.model.OrganizationCommandModel;

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
    public Organization addOrganization(OrganizationCommandModel orgCM)
	    throws InvalidObjectException, DuplicateObjectException {
	Organization org = convertFromCommand(orgCM);
	return addOrganization(org);
    }
    
    @Override
    public void removeOrganization(Organization org)
	    throws NotFoundException {
	if (!exists(org)) {
	    throw new NotFoundException("Organization not found.");
	}
	orgDao.removeOrganization(org);
    }
    
    @Override
    public void removeOrganization(OrganizationCommandModel orgCM)
	    throws NotFoundException {
	Organization org = convertFromCommand(orgCM);
	removeOrganization(org);
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
    
    private boolean exists(Organization org) {
	Organization result = getOrganizationById(org.getId());
	return (result != null);
    }
    
    private Organization convertFromCommand(OrganizationCommandModel orgCM) {
	Organization org = new Organization();
	
	int id = orgCM.getId();
	if (id != 0) {
	    org.setId(id);
	}
	
	org.setName(orgCM.getName());
	org.setHead(orgCM.getHead());
	org.setDescription(orgCM.getDescription());
	
	return org;
    }
    
    private OrganizationCommandModel convertToCommand(Organization org) {
	OrganizationCommandModel orgCM = new OrganizationCommandModel();
	orgCM.setId(org.getId());
	orgCM.setName(org.getName());
	orgCM.setHead(org.getHead());
	orgCM.setDescription(org.getDescription());
	
	return orgCM;
    }
}
