/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingCommand;
import com.sg.superperson2.model.SightingView;

/**
 *
 * @author main
 */
public interface SightingService {
    
    public Sighting addSighting(Sighting sighting)
	    throws InvalidObjectException;
    
    public Sighting addSighting(SightingCommand sightingCM)
	    throws InvalidObjectException;
    
    public void removeSighting(Sighting sighting)
	    throws NotFoundException;
    
    public void removeSighting(SightingCommand sightingCM)
	    throws NotFoundException;
    
    public void updateSighting(Sighting sighting)
	    throws InvalidObjectException;
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getSightings(int limit);
    
    public Sighting getSightingById(int id);
        
    public SightingCommand getSightingCommandById(int id);
    
    public List<SightingView> getAllSightingViews();
    
    public List<SightingView> getSightingViews(int limit);
	
    public SightingView getSightingViewById(int id);
}
