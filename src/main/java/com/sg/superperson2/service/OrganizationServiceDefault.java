/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.OrganizationDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.OrganizationCommand;
import com.sg.superperson2.model.OrganizationView;

/**
 *
 * @author main
 */
public class OrganizationServiceDefault implements OrganizationService {
    
    @Inject
    AddressService adrService;
    
    @Inject
    LocationService locService;
    
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
    public Organization addOrganization(OrganizationCommand orgCM)
	    throws InvalidObjectException, DuplicateObjectException {
	Organization org = convertFromCommand(orgCM);
	return addOrganization(org);
    }
    
    @Override
    public void removeOrganization(Organization org) {
	orgDao.removeOrganization(org);
    }
    
    @Override
    public void removeOrganization(OrganizationCommand orgCM) {
	Organization org = convertFromCommand(orgCM);
	removeOrganization(org);
    }
    
    @Override
    public void updateOrganization(Organization org)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(org)) {
	    throw new InvalidObjectException("Organization invalid.");
	}
	if (isDuplicate(org)) {
	    throw new DuplicateObjectException("Organization is a duplicate.");
	}
	
	orgDao.updateOrganization(org);
    }
    
    @Override
    public void updateOrganization(OrganizationCommand orgCom)
	    throws InvalidObjectException, DuplicateObjectException {
	Organization org = convertFromCommand(orgCom);
	updateOrganization(org);
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
	return orgDao.getAllOrganizations();
    }
    
    @Override
    public Organization getOrganizationById(int id) {
	return orgDao.getOrganizationById(id);
    }
    
    @Override
    public OrganizationCommand getOrganizationCommandById(int id) {
	Organization org = getOrganizationById(id);
	OrganizationCommand orgCom = convertToCommand(org);
	
	return orgCom;
    }
    
    @Override
    public List<OrganizationView> getAllOrganizationViews() {
	List<Organization> orgs = getAllOrganizations();
	List<OrganizationView> orgViews = convertToView(orgs);
	
	return orgViews;
    }
    
    @Override
    public OrganizationView getOrganizationViewById(int id) {
	Organization org = getOrganizationById(id);
	OrganizationView orgView = convertToView(org);
	
	return orgView;
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
	    
	    // Skip if same id
	    if (currentOrg.getId() != org.getId()) {
		if (isMatch(currentOrg, org)) {
		    return true;
		}
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
    
    private Organization convertFromCommand(OrganizationCommand orgCom) {
	Organization org = new Organization();
	
	int id = orgCom.getId();
	if (id != 0) {
	    org.setId(id);
	}
	
	org.setName(orgCom.getName());
	org.setHead(orgCom.getHead());
	org.setDescription(orgCom.getDescription());
	
	Integer locId = orgCom.getLocationId();
	if (locId != 0) {
	    Location loc = new Location();
	    loc.setId(locId);
	    org.setLocation(loc);
	}
	
	return org;
    }
    
    private OrganizationCommand convertToCommand(Organization org) {
	OrganizationCommand orgCom = new OrganizationCommand();
	orgCom.setId(org.getId());
	orgCom.setName(org.getName());
	orgCom.setHead(org.getHead());
	orgCom.setDescription(org.getDescription());
	
	int locId = org.getLocation().getId();
	if (locId != 0) {
	    orgCom.setLocationId(locId);
	}
	
	return orgCom;
    }
    
    private OrganizationView convertToView(Organization org) {
	OrganizationView orgView = new OrganizationView();
	orgView.setId(org.getId());
	orgView.setName(org.getName());
	orgView.setHead(org.getHead());
	orgView.setDescription(org.getDescription());
	
	// Fully load location, address
	int locId = org.getLocation().getId();
	Location loc = locService.getLocationById(locId);
	if (loc != null) {
	    int adrId = loc.getAddress().getId();
	    Address adr = adrService.getAddressById(adrId);
	    if (adr != null) {
		loc.setAddress(adr);
	    }
	    orgView.setLocation(loc);
	}
	
	return orgView;
    }
    
    private List<OrganizationView> convertToView(List<Organization> orgs) {
	List<OrganizationView> orgViews = new ArrayList<>();
	for (Organization currentOrg : orgs) {
	    OrganizationView orgView = convertToView(currentOrg);
	    orgViews.add(orgView);
	}
	
	return orgViews;
    }
}
