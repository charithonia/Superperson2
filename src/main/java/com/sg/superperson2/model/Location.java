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
public class Location {
    private int id;
    private String name;
    private double longitude;
    private double latitude;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 29 * hash + this.id;
	hash = 29 * hash + Objects.hashCode(this.name);
	hash = 29 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
	hash = 29 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
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
	final Location other = (Location) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
	    return false;
	}
	if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
	    return false;
	}
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	return true;
    }
}
