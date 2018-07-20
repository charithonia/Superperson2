/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingSuperperson;

/**
 *
 * @author main
 */
public interface SightingSuperpersonDao {
    
    public SightingSuperperson addSightingSuperperson(
	    SightingSuperperson sightingSuperperson);
    
    public void removeSightingSuperperson(SightingSuperperson
	    sightingSuperperson);
    
    public List<SightingSuperperson> getAllSightingSuperpersons();
    
    public SightingSuperperson getSightingSuperpersonById(int id);
    
    public List<SightingSuperperson> getSightingSuperpersonsBySighting(
	    Sighting sighting);
}
