/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import com.sg.superperson2.model.SightingView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.SightingDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingCommand;
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
    LocationService locService;
    
    @Inject
    SuperpersonService supService;
    
    @Inject
    UserService usrService;
    
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
    public Sighting addSighting(SightingCommand sigCom)
	    throws InvalidObjectException {
	Sighting sig = convertFromCommand(sigCom);
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
    public void removeSighting(SightingCommand sigCom)
	    throws NotFoundException {
	Sighting sig = convertFromCommand(sigCom);
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
    public List<SightingCommand> getAllSightingCommands() {
	List<SightingCommand> sigComs = new ArrayList<>();
	List<Sighting> sigs = getAllSightings();
	for (Sighting sig : sigs) {
	    SightingCommand sigCom = convertToCommand(sig);
	    sigComs.add(sigCom);
	}
	
	return sigComs;
    }
    
    @Override
    public SightingCommand getSightingCommandById(int id) {
	Sighting sig = getSightingById(id);
	SightingCommand sigCom = convertToCommand(sig);
	
	return sigCom;
    }
    
    @Override
    public List<SightingView> getAllSightingViews() {
	List<Sighting> allSigs = getAllSightings();
	List<SightingView> sigViews = new ArrayList<>();
	for (Sighting currentSig : allSigs) {
	    SightingView sigView = convertToView(currentSig);
	    sigViews.add(sigView);
	}
	
	return sigViews;
    }
    
    @Override
    public SightingView getSightingViewById(int id) {
	Sighting sig = getSightingById(id);
	SightingView sigView = convertToView(sig);
	
	return sigView;
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
    
    private Sighting convertFromCommand(SightingCommand sigCom) {
	Sighting sig = new Sighting();
	
	int id = sigCom.getId();
	if (id != 0) {
	    sig.setId(id);
	}
	
	sig.setTimestamp(sigCom.getTimestamp());
	
	Location loc = new Location();
	loc.setId(sigCom.getLocationId());
	sig.setLocation(loc);
	
	List<Superperson> sups = new ArrayList<>();
	for (Integer supId : sigCom.getSuperpersonIds()) {
	    Superperson sup = new Superperson();
	    sup.setId(supId);
	}
	sig.setSuperpersons(sups);
	
	User user = new User();
	user.setId(sigCom.getUserId());
	sig.setUser(user);
	
	return sig;
    }
    
    private SightingCommand convertToCommand(Sighting sig) {
	SightingCommand sigCom = new SightingCommand();
	
	int id = sig.getId();
	if (id != 0) {
	    sigCom.setId(id);
	}
	
	sigCom.setTimestamp(sig.getTimestamp());
	sigCom.setLocationId(sig.getLocation().getId());
	// TODO:
	// Finish?
	
	return sigCom;
    }
    
    /**
     * Create a Sighting model where all components are fully loaded.
     * @param sig The Sighting to modify
     * @return The Sighting fully loaded
     */
    private SightingView convertToView(Sighting sig) {
	SightingView sigView = new SightingView();
	sigView.setId(sig.getId());
	sigView.setTimestamp(sig.getTimestamp());
	
	// Load location
	int locId = sig.getLocation().getId();
	Location loc = locService.getLocationById(locId);
	sigView.setLocation(loc);
	
	// Load superpeople
	List<Superperson> sups = new ArrayList<>();
	for (Superperson currentSup : sig.getSuperpersons()) {
	    int supId = currentSup.getId();
	    Superperson sup = supService.getSuperpersonById(supId);
	    sups.add(sup);
	}
	sigView.setSuperpersons(sups);
	
	// Load user
	int usrId = sig.getUser().getId();
	User usr = usrService.getUserById(usrId);
	sigView.setUser(usr);
	
	return sigView;
    }
}
