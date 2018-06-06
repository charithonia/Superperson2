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
    private double latitude;
    private double longitude;
    
    private Address address;

    public Location() {
	Address blankAddress = new Address();
	address = blankAddress;
    }
    
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

    public double getLatitude() {
	return latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }
    
    public Address getAddress() {
	return address;
    }
    
    public void setAddress(Address address) {
	this.address = address;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 59 * hash + this.id;
	hash = 59 * hash + Objects.hashCode(this.name);
	hash = 59 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
	hash = 59 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
	hash = 59 * hash + Objects.hashCode(this.address);
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
	if (!Objects.equals(this.address, other.address)) {
	    return false;
	}
	return true;
    }
}
