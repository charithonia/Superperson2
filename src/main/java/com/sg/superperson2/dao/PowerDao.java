/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public interface PowerDao {
    
    public Power addPower(Power power);
    
    public void removePower(Power power);
    
    public void updatePower(Power power);
    
    public List<Power> getAllPowers();
    
    public Power getPowerById(int id);
        
    public List<Power> getPowersBySuperperson(Superperson superperson);
}
