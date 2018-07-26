/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.LocationDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.LocationCommand;

/**
 *
 * @author main
 */
public class LocationServiceDefault implements LocationService {
    
    @Inject
    LocationDao locDao;
    
    @Inject
    AddressService adrService;
    
    @Override
    public Location addLocation(Location location)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(location)) {
	    throw new InvalidObjectException("Location invalid.");
	}
	if (isDuplicate(location)) {
	    throw new DuplicateObjectException("Location is a duplicate.");
	}
	    
	Address address = location.getAddress();
	if (address != null) {
	    address = adrService.addAddress(address);
	    location.setAddress(address);
	}

	return locDao.addLocation(location);
    }
    
    @Override
    public Location addLocation(LocationCommand locCM)
	    throws InvalidObjectException, DuplicateObjectException {
	Location loc = convertFromCommand(locCM);
	return addLocation(loc);
    }
    
    @Override
    public void removeLocation(Location location)
	    throws NotFoundException, DeleteLinkedObjectException {
	if (!exists(location)) {
	    throw new NotFoundException("Location not found.");
	}
	locDao.removeLocation(location);
    }
    
    @Override
    public void removeLocation(LocationCommand locCM)
	    throws NotFoundException, DeleteLinkedObjectException {
	Location loc = convertFromCommand(locCM);
	removeLocation(loc);
    }
    
    @Override
    public void updateLocation(Location location)
	    throws InvalidObjectException {
	if (!isValid(location)) {
	    throw new InvalidObjectException();
	}
	
	Address address = location.getAddress();
	if (address != null) {
	    address = adrService.addAddress(address);
	    location.setAddress(address);
	}
	
	locDao.updateLocation(location);
    }
    
    @Override
    public void updateLocation(LocationCommand locCM)
	    throws InvalidObjectException {
	Location loc = convertFromCommand(locCM);
	updateLocation(loc);
    }
    
    @Override
    public List<Location> getAllLocations() {
	return locDao.getAllLocations();
    }
    
    @Override
    public Location getLocationById(int id) {
	return locDao.getLocationById(id);
    }
    
    private void validate(Location loc)
	    throws InvalidObjectException, DuplicateObjectException {
	boolean isValid = isValid(loc);
	if (!isValid) {
	    throw new InvalidObjectException("Location invalid.");
	}
	
	if (isDuplicate(loc)) {
	    throw new DuplicateObjectException("Location is a duplicate.");
	}
    }
        
    private boolean isValid(Location loc) {
	double degrees;
	
	degrees = loc.getLatitude();
	if (degrees < -90 || degrees > 90) {
	    return false;
	}
	
	degrees = loc.getLongitude();
	if (degrees < -180 || degrees > 180) {
	    return false;
	}
	
	return true;
    }
    
    private boolean isDuplicate(Location loc) {
	List<Location> allLocs = getAllLocations();
	for (Location currentLoc : allLocs) {
	    if (isMatch(currentLoc, loc)) {
		return true;
	    }
	}
	return false;
    }
    
    private boolean isMatch(Location loc1, Location loc2) {
	boolean matches = true;
	if (loc1.getLatitude() != loc2.getLatitude()) {
	    matches = false;
	}
	else if (loc1.getLongitude() != loc2.getLongitude()) {
	    matches = false;
	}

	return (matches);
    }
    
    private boolean exists(Location loc) {
	Location result = getLocationById(loc.getId());
	return !(result == null);
    }
    
    private boolean isLinked(Location loc) {
	
	// TODO: finish when there's time, for other classes too
	boolean isLinked = false;
	
	return isLinked;
    }
    
    private LocationCommand convertToCommand(Location loc) {
	LocationCommand locCM = new LocationCommand();
	locCM.setId(loc.getId());
	locCM.setLatitude(loc.getLatitude());
	locCM.setLongitude(loc.getLongitude());
	locCM.setName(loc.getName());
	
	return locCM;
    }
    
    private Location convertFromCommand(LocationCommand locCM) {
	Location loc = new Location();
	
	int id = locCM.getId();
	if (id != 0) {
	    loc.setId(id);
	}
	
	loc.setLatitude(locCM.getLatitude());
	loc.setLongitude(locCM.getLongitude());
	loc.setName(locCM.getName());
	
	return loc;
    }
}
