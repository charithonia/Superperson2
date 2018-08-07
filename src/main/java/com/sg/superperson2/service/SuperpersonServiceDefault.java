/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.SuperpersonDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonCommand;
import com.sg.superperson2.model.SuperpersonView;

/**
 *
 * @author main
 */
public class SuperpersonServiceDefault implements SuperpersonService {
    
    @Inject
    OrganizationService orgService;
    
    @Inject
    PowerService powService;
    
    @Inject
    SuperpersonDao supDao;
    
    @Override
    public Superperson addSuperperson(Superperson sup)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(sup)) {
	    throw new InvalidObjectException("Superperson invalid.");
	}
	if(isDuplicate(sup)) {
	    throw new DuplicateObjectException("Superperson is a duplicate.");
	}
	
	return supDao.addSuperperson(sup);
    }
    
    @Override
    public Superperson addSuperperson(SuperpersonCommand supCom)
	    throws InvalidObjectException, DuplicateObjectException {
	Superperson sup = convertFromCommand(supCom);
	return addSuperperson(sup);
    }
    
    @Override
    public void removeSuperperson(Superperson sup) {
	supDao.removeSuperperson(sup);
    }
    
    @Override
    public void removeSuperpersonById(int id) {
	Superperson sup = getSuperpersonById(id);
	removeSuperperson(sup);
    }
    
    @Override
    public void updateSuperperson(Superperson sup)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(sup)) {
	    throw new InvalidObjectException("Superperson invalid.");
	}
	if (isDuplicate(sup)) {
	    throw new DuplicateObjectException("Superperson is a duplicate.");
	}
	
	supDao.updateSuperperson(sup);
    }
    
    @Override
    public void updateSuperperson(SuperpersonCommand supCom)
	    throws InvalidObjectException, DuplicateObjectException {
	Superperson sup = convertFromCommand(supCom);
	updateSuperperson(sup);
    }
    
    @Override
    public List<Superperson> getAllSuperpersons() {
	return supDao.getAllSuperpersons();
    }
    
    @Override
    public Superperson getSuperpersonById(int id) {
	return supDao.getSuperpersonById(id);
    }
    
    @Override
    public SuperpersonCommand getSuperpersonCommandById(int id) {
	Superperson sup = getSuperpersonById(id);
	SuperpersonCommand supCom = convertToCommand(sup);
	
	return supCom;
    }
    
    @Override
    public List<SuperpersonView> getAllSuperpersonViews() {
	List<Superperson> sups = getAllSuperpersons();
	List<SuperpersonView> supViews = convertToView(sups);
	
	return supViews;
    }
    
    @Override
    public SuperpersonView getSuperpersonViewById(int id) {
	Superperson sup = getSuperpersonById(id);
	SuperpersonView supView = convertToView(sup);
	
	return supView;
    }
    
    private Superperson convertFromCommand(SuperpersonCommand supCom) {
	Superperson sup = new Superperson();
	sup.setId(supCom.getId());
	sup.setName(supCom.getName());
	sup.setDescription(supCom.getDescription());
	
	List<Organization> orgs = new ArrayList<>();
	for (int id : supCom.getOrganizationIds()) {
	    Organization org = new Organization();
	    org.setId(id);
	    orgs.add(org);
	}
	sup.setOrganizations(orgs);
	
	List<Power> pows = new ArrayList<>();
	for (int id : supCom.getPowerIds()) {
	    Power pow = new Power();
	    pow.setId(id);
	    pows.add(pow);
	}
	sup.setPowers(pows);
	
	return sup;
    }
    
    private SuperpersonCommand convertToCommand(Superperson sup) {
	SuperpersonCommand supCom = new SuperpersonCommand();
	
	int id = sup.getId();
	if (id != 0) {
	    supCom.setId(id);
	}
	
	supCom.setName(sup.getName());
	supCom.setDescription(sup.getDescription());
	
	List<Integer> orgIds = new ArrayList<>();
	for (Organization currentOrg : sup.getOrganizations()) {
	    orgIds.add(currentOrg.getId());
	}
	supCom.setOrganizationIds(orgIds);
	
	List<Integer> powIds = new ArrayList<>();
	for (Power currentPow : sup.getPowers()) {
	    powIds.add(currentPow.getId());
	}
	supCom.setPowerIds(powIds);
	
	return supCom;
    }
    
    private SuperpersonView convertToView(Superperson sup) {
	SuperpersonView supView = new SuperpersonView();
	supView.setId(sup.getId());
	supView.setName(sup.getName());
	supView.setDescription(sup.getDescription());
	
	// Fully load organizations and powers
	List<Organization> orgs = new ArrayList<>();
	for (Organization currentOrg : sup.getOrganizations()) {
	    int id = currentOrg.getId();
	    Organization org = orgService.getOrganizationById(id);
	    orgs.add(org);
	}
	supView.setOrganizations(orgs);
	
	List<Power> pows = new ArrayList<>();
	for(Power currentPow : sup.getPowers()) {
	    int id = currentPow.getId();
	    Power pow = powService.getPowerById(id);
	    pows.add(pow);
	}
	supView.setPowers(pows);
	
	return supView;
    }
    
    private List<SuperpersonView> convertToView(List<Superperson> sups) {
	List<SuperpersonView> supViews = new ArrayList<>();
	for (Superperson currentSup : sups) {
	    SuperpersonView supView = convertToView(currentSup);
	    supViews.add(supView);
	}
	
	return supViews;
    }
    
    private boolean isValid(Superperson sup) {
	String strField;
	
	strField = sup.getName();
	if (strField == null || strField.trim().equals("")) {
	    return false;
	}
	
	return true;
    }
    
    private boolean isDuplicate(Superperson sup) {
	List<Superperson> allSups = getAllSuperpersons();
	for (Superperson currentSup : allSups) {
	    if (isMatch(currentSup, sup)) {
		return true;
	    }
	}
	return false;
    }
    
    private boolean isMatch(Superperson sup1, Superperson sup2) {
	
	// Skip if same id
	if (sup1.getId() == sup2.getId()) {
	    return false;
	}
	
	return sup1.getName().equalsIgnoreCase(sup2.getName());
    }
}
