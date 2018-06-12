/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.SuperpersonPower;

/**
 *
 * @author main
 */
public interface SuperpersonPowerDao {
    
    public SuperpersonPower addSuperpersonPower(SuperpersonPower superpersonPower);
    
    public void removeSuperpersonPower(SuperpersonPower superpersonPower);
    
    public List<SuperpersonPower> getAllSuperpersonPowers();
    
    public SuperpersonPower getSuperpersonPowerById(int id);
}
