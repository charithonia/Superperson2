/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.PowerDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.PowerCommand;
import com.sg.superperson2.model.PowerView;

/**
 *
 * @author main
 */
public class PowerServiceDefault implements PowerService {
    
    @Inject
    PowerDao powDao;
    
    @Override
    public Power addPower(Power pow)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(pow)) {
	    throw new InvalidObjectException("Power is invalid.");
	}
	if (isDuplicate(pow)) {
	    throw new DuplicateObjectException("Power is a duplicate.");
	}
	
	return powDao.addPower(pow);
    }
    
    @Override
    public Power addPower(PowerCommand powCom)
	    throws InvalidObjectException, DuplicateObjectException {
	Power pow = convertFromCommand(powCom);
	return addPower(pow);
    }
    
    @Override
    public Power removePower(Power pow) {
	if (!exists(pow)) {
	    return null;
	}
	return powDao.removePower(pow);
    }
    
    // !
    // Remove?
    @Override
    public Power removePower(PowerCommand powCom) {
	Power pow = convertFromCommand(powCom);
	return removePower(pow);
    }
    
    @Override
    public Power removePowerById(int id) {
	Power pow = new Power();
	pow.setId(id);
	
	return removePower(pow);
    }
    
    @Override
    public void updatePower(Power pow)
	    throws InvalidObjectException, DuplicateObjectException {
	if (!isValid(pow)) {
	    throw new InvalidObjectException("Power is invalid.");
	}
	if (isDuplicate(pow)) {
	    throw new DuplicateObjectException("Power is a duplicate.");
	}
	powDao.updatePower(pow);
    }
    
    @Override
    public List<Power> getAllPowers() {
	return powDao.getAllPowers();
    }
    
    @Override
    public Power getPowerById(int id) {
	return powDao.getPowerById(id);
    }
    
    @Override
    public PowerCommand getPowerCommandById(int id) {
	Power pow = getPowerById(id);
	PowerCommand powCom = convertToCommand(pow);
	
	return powCom;
    }
    
    @Override
    public List<PowerView> getAllPowerViews() {
	List<Power> pows = getAllPowers();
	List<PowerView> powViews = convertToView(pows);
	
	return powViews;
    }
    
    @Override
    public PowerView getPowerViewById(int id) {
	Power pow = getPowerById(id);
	PowerView powView = convertToView(pow);
	
	return powView;
    }
    
    @Override
    public Power convertFromCommand(PowerCommand powCM) {
	Power pow = new Power();
	
	int id = powCM.getId();
	if (id != 0) {
	    pow.setId(id);
	}
	
	pow.setName(powCM.getName());
	pow.setDescription(powCM.getDescription());
	
	return pow;
    }
    
    @Override
    public PowerCommand convertToCommand(Power pow) {
	PowerCommand powCM = new PowerCommand();
	powCM.setId(pow.getId());
	powCM.setName(pow.getName());
	powCM.setDescription(pow.getDescription());
	
	return powCM;
    }
    
    private PowerView convertToView(Power pow) {
	PowerView powView = new PowerView();
	powView.setId(pow.getId());
	powView.setName(pow.getName());
	powView.setDescription(pow.getDescription());
	
	return powView;
    }
    
    private List<PowerView> convertToView(List<Power> pows) {
	List<PowerView> powViews = new ArrayList<>();
	for (Power pow : pows) {
	    PowerView powView = convertToView(pow);
	    powViews.add(powView);
	}
	
	return powViews;
    }
    
    private boolean isValid(Power power) {
	String name = power.getName();
	if (name == null || name.trim().equals("")) {
	    return false;
	}
	
	return true;
    }
    
    private boolean isDuplicate(Power power) {
	List<Power> allPowers = getAllPowers();
	for (Power currentPower : allPowers) {
	    if (isMatch(currentPower, power)) {
		return true;
	    }
	}
	return false;
    }
    
    private boolean isMatch(Power pow1, Power pow2) {
	if (pow1.getName()
		.equalsIgnoreCase(pow2.getName())) {
	    return true;
	}
	return false;
    }
    
    private boolean exists(Power power) {
	Power result = getPowerById(power.getId());
	return (result != null);
    }
}
