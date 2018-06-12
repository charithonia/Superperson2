/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

/**
 *
 * @author main
 */
public class SuperpersonPower {
    private int id;
    private Superperson superperson;
    private Power power;

    public int getId() {
	return id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public Superperson getSuperperson() {
	return superperson;
    }

    public void setSuperperson(Superperson superperson) {
	this.superperson = superperson;
    }

    public Power getPower() {
	return power;
    }

    public void setPower(Power power) {
	this.power = power;
    }
}
