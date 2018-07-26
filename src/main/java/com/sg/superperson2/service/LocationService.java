/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.LocationCommand;

/**
 *
 * @author main
 */
public interface LocationService {
    
    public Location addLocation(Location location)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public Location addLocation(LocationCommand locCM)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void removeLocation(Location location)
	    throws NotFoundException, DeleteLinkedObjectException;
    
    public void removeLocation(LocationCommand locCM)
	    throws NotFoundException, DeleteLinkedObjectException;
    
    public void updateLocation(Location location)
	    throws InvalidObjectException;
    
    public void updateLocation(LocationCommand locCM)
	    throws InvalidObjectException;
    
    public List<Location> getAllLocations();
    
    public Location getLocationById(int id);
}
