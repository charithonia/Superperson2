/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.SightingDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingCommandModel;
import com.sg.superperson2.model.SightingSuperperson;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
public class SightingServiceDefault implements SightingService {
    
    @Inject
    SightingDao sigDao;
    
    @Inject
    SuperpersonService supService;
    
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
    public Sighting addSighting(SightingCommandModel sigCM)
	    throws InvalidObjectException {
	Sighting sig = convertFromCommand(sigCM);
	return addSighting(sig);
    }
    
    @Override
    public void removeSighting(Sighting sig)
	    throws NotFoundException {
	if (!exists(sig)) {
	    throw new NotFoundException("Sighting not found.");
	}
	sigDao.removeSighting(sig);
    }
    
    @Override
    public void removeSighting(SightingCommandModel sigCM)
	    throws NotFoundException {
	Sighting sig = convertFromCommand(sigCM);
	removeSighting(sig);
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
    
    @Override
    public List<SightingCommandModel> getAllSightingCommands() {
	List<SightingCommandModel> sigCMs = new ArrayList<>();
	List<Sighting> sigs = getAllSightings();
	for (Sighting sig : sigs) {
	    SightingCommandModel sigCM = convertToCommand(sig);
	    sigCMs.add(sigCM);
	}
	
	return sigCMs;
    }
    
    @Override
    public SightingCommandModel getSightingCommandById(int id) {
	Sighting sig = getSightingById(id);
	SightingCommandModel sigCM = convertToCommand(sig);
	
	return sigCM;
    }
    
    private boolean isValid(Sighting sig) {
	if (sig.getUser() == null) {
	    return false;
	}
	return true;
    }
    
    private boolean exists(Sighting sig) {
	Sighting result = getSightingById(sig.getId());
	return (result != null);
    }
    
    private Sighting convertFromCommand(SightingCommandModel sigCM) {
	Sighting sig = new Sighting();
	
	
	return sig;
    }
    
    private SightingCommandModel convertToCommand(Sighting sig) {
	SightingCommandModel sigCM = new SightingCommandModel();
	
	int id = sig.getId();
	if (id != 0) {
	    sigCM.setId(id);
	}
	
	sigCM.setTimestamp(sig.getTimestamp());
	sigCM.setLocationId(sig.getLocation().getId());
	// TODO:
	// Finish this class once model changes are done
	
	return sigCM;
    }
    
    
}
