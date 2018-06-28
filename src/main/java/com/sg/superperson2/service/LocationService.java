/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.LocationCommandModel;

/**
 *
 * @author main
 */
public interface LocationService {
    
    public Location addLocation(Location location)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public Location addLocation(LocationCommandModel locCM)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void removeLocation(Location location)
	    throws NotFoundException;
    
    public void removeLocation(LocationCommandModel locCM)
	    throws NotFoundException;
    
    public void updateLocation(Location location)
	    throws InvalidObjectException;
    
    public void updateLocation(LocationCommandModel locCM)
	    throws InvalidObjectException;
    
    public List<Location> getAllLocations();
    
    public Location getLocationById(int id);
}
