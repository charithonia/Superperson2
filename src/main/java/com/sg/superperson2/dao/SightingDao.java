/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.Sighting;

/**
 *
 * @author main
 */
public interface SightingDao {
    
    public void addSighting(Sighting sighting);
    
    public void removeSighting(Sighting sighting);
    
    public void updateSighting(Sighting sighting);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsSortByDate();
    
    public List<Sighting> getAllSightingsSortByDate(int limit);
    
    public Sighting getSightingById(int id);
}
