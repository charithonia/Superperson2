/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 89 * hash + this.id;
	hash = 89 * hash + Objects.hashCode(this.sighting);
	hash = 89 * hash + Objects.hashCode(this.superperson);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final SightingSuperperson other = (SightingSuperperson) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.sighting, other.sighting)) {
	    return false;
	}
	if (!Objects.equals(this.superperson, other.superperson)) {
	    return false;
	}
	return true;
    }
}
