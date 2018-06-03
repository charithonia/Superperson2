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
public class SightingSuperperson {
    private int id;
    private Sighting sighting;
    private Superperson superperson;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Sighting getSighting() {
	return sighting;
    }

    public void setSighting(Sighting sighting) {
	this.sighting = sighting;
    }

    public Superperson getSuperperson() {
	return superperson;
    }

    public void setSuperperson(Superperson superperson) {
	this.superperson = superperson;
    }
}
