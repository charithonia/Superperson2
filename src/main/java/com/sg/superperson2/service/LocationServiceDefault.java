/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.LocationDao;
import com.sg.superperson2.exception.InvalidObjectException;
import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;

/**
 *
 * @author main
 */
public class LocationServiceDefault implements LocationService {
    
    @Inject
    LocationDao locDao;
    
    @Inject
    AddressService addressService;
    
    @Override
    public Location addLocation(Location location)
	    throws InvalidObjectException {
	if (isValid(location)) {
	    
	    // Add and assign address if present
	    Address address = location.getAddress();
	    if (address != null) {
		address = addressService.addAddress(address);
		location.setAddress(address);
	    }
	    
	    return locDao.addLocation(location);
	}
	else {
	    throw new InvalidObjectException("Location invalid!");
	}
    }
    
    @Override
    public void removeLocation(Location location) {
	locDao.removeLocation(location);
    }
    
    @Override
    public void updateLocation(Location location) {
	
	// Update address if included
	Address address = location.getAddress();
	if (address != null) {
	    try {
		address = addressService.addAddress(address);
		location.setAddress(address);
	    }
	    catch (InvalidObjectException ex) {}
	}
	
	locDao.updateLocation(location);
    }
    
    @Override
    public List<Location> getAllLocations() {
	return locDao.getAllLocations();
    }
    
    @Override
    public Location getLocationById(int id) {
	return locDao.getLocationById(id);
    }
    
    private boolean isDuplicate(Location loc1, Location loc2) {
	if (loc1.getLatitude() != loc2.getLatitude()) {
	    return false;
	}
	if (loc1.getLongitude() != loc2.getLongitude()) {
	    return false;
	}
	if (!loc1.getName().equalsIgnoreCase(loc2.getName())) {
	    return false;
	}
	return true;
    }
    
    private boolean isValid(Location loc) {
	
	// Valid latitude/longitude
	double degrees;
	
	degrees = loc.getLatitude();
	if (degrees < -90 || degrees > 90) {
	    return false;
	}
	
	degrees = loc.getLongitude();
	if (degrees < -180 || degrees > 180) {
	    return false;
	}
	
	// Prevent duplicates
	List<Location> allLocs = getAllLocations();
	for (Location currentLoc : allLocs) {
	    if (isDuplicate(currentLoc, loc)) {
		return false;
	    }
	}
	
	return true;
    }
}
