/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.SuperpersonDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public class SuperpersonServiceDefault implements SuperpersonService {
    
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
    public void removeSuperperson(Superperson sup) {
	supDao.removeSuperperson(sup);
    }
    
    @Override
    public void updateSuperperson(Superperson sup)
	    throws InvalidObjectException {
	if (!isValid(sup)) {
	    throw new InvalidObjectException("Superperson invalid.");
	}
	
	supDao.updateSuperperson(sup);
    }
    
    @Override
    public List<Superperson> getAllSuperpersons() {
	return supDao.getAllSuperpersons();
    }
    
    @Override
    public Superperson getSuperpersonById(int id) {
	return supDao.getSuperpersonById(id);
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
	return sup1.getName().equalsIgnoreCase(sup2.getName());
    }
}
