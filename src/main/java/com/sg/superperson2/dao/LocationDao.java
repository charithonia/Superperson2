/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.Location;

/**
 *
 * @author main
 */
public interface LocationDao {
    
    public void addLocation(Location location);
    
    public void removeLocation(Location location);
    
    public void updateLocation(Location location);
    
    public List<Location> getAllLocations();
    
    public Location getLocationById();
}
