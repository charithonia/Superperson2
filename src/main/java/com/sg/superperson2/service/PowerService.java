/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.PowerCommand;
import com.sg.superperson2.model.PowerView;

/**
 *
 * @author main
 */
public interface PowerService {
    
    public Power addPower(Power power)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public Power addPower(PowerCommand powerCM)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public Power removePower(Power power);
    
    public Power removePowerById(int id);
    
    public void updatePower(Power power)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void updatePower(PowerCommand powerCommand)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public List<Power> getAllPowers();
    
    public Power getPowerById(int id);
    
    public PowerCommand getPowerCommandById(int id);
    
    public List<PowerView> getAllPowerViews();
    
    public PowerView getPowerViewById(int id);
    
    public Power convertFromCommand(PowerCommand powerCommand);
        
    public PowerCommand convertToCommand(Power power);
}
