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
public class Address {
    private int id;
    private int number;
    private String street;
    private String city;
    private String state;
    private int zip;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public int getZip() {
	return zip;
    }

    public void setZip(int zip) {
	this.zip = zip;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 29 * hash + this.id;
	hash = 29 * hash + this.number;
	hash = 29 * hash + Objects.hashCode(this.street);
	hash = 29 * hash + Objects.hashCode(this.city);
	hash = 29 * hash + Objects.hashCode(this.state);
	hash = 29 * hash + this.zip;
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
	final Address other = (Address) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (this.number != other.number) {
	    return false;
	}
	if (this.zip != other.zip) {
	    return false;
	}
	if (!Objects.equals(this.street, other.street)) {
	    return false;
	}
	if (!Objects.equals(this.city, other.city)) {
	    return false;
	}
	if (!Objects.equals(this.state, other.state)) {
	    return false;
	}
	return true;
    }
}
