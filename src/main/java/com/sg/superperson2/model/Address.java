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
    private String number;
    private String street;
    private String city;
    private String state;
    private String zip;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
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

    public String getZip() {
	return zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 41 * hash + this.id;
	hash = 41 * hash + Objects.hashCode(this.number);
	hash = 41 * hash + Objects.hashCode(this.street);
	hash = 41 * hash + Objects.hashCode(this.city);
	hash = 41 * hash + Objects.hashCode(this.state);
	hash = 41 * hash + Objects.hashCode(this.zip);
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
	if (!Objects.equals(this.number, other.number)) {
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
	if (!Objects.equals(this.zip, other.zip)) {
	    return false;
	}
	return true;
    }
}
