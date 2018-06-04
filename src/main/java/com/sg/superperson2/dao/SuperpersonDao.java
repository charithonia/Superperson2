/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public interface SuperpersonDao {
    
    public void addSuperperson(Superperson superperson);
    
    public void removeSuperperson(Superperson superperson);
    
    public void updateSuperperson(Superperson superperson);
    
    public List<SuperpersonDao> getAllSuperpersons();
    
    public SuperpersonDao getSuperpersonById(int id);
    
    public SuperpersonDao getSuperpersonByName();
    
    public List<SuperpersonDao> getSuperpersonsBySighting(Sighting sighting);
}
