/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.SightingDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Sighting;

/**
 *
 * @author main
 */
public class SightingServiceDefault implements SightingService {
    
    @Inject
    SightingDao sigDao;
    
    @Override
    public Sighting addSighting(Sighting sig)
	    throws InvalidObjectException {
	if (!isValid(sig)) {
	    throw new InvalidObjectException("Sighting invalid.");
	}
	
	LocalDateTime datetime = LocalDateTime.now();
	sig.setTimestamp(datetime);
	
	return sigDao.addSighting(sig);
    }
    
    @Override
    public void removeSighting(Sighting sig) {
	sigDao.removeSighting(sig);
    }
    
    @Override
    public void updateSighting(Sighting sig)
	    throws InvalidObjectException {
	if (!isValid(sig)) {
	    throw new InvalidObjectException("Sighting invalid.");
	}
	
	sigDao.updateSighting(sig);
    }
    
    @Override
    public List<Sighting> getAllSightings() {
	return sigDao.getAllSightings();
    }
    
    @Override
    public Sighting getSightingById(int id) {
	return sigDao.getSightingById(id);
    }
    
    private boolean isValid(Sighting sig) {
	if (sig.getUser() == null) {
	    return false;
	}
	return true;
    }
}
