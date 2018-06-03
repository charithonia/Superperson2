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
    private Superperson person;
    private Power power;

    public int getId() {
	return id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public Superperson getPerson() {
	return person;
    }

    public void setPerson(Superperson person) {
	this.person = person;
    }

    public Power getPower() {
	return power;
    }

    public void setPower(Power power) {
	this.power = power;
    }
}
