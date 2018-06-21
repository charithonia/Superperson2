/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Power;

/**
 *
 * @author main
 */
public interface PowerService {
    
    public Power addPower(Power power)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void removePower(Power power);
    
    public void updatePower(Power power)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public List<Power> getAllPowers();
    
    public Power getPowerById(int id);
}
