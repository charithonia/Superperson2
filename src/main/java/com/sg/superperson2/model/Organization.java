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
public class Organization {
    private int id;
    private String name;
    
    private Address address;
    private Person leader;

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

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public Person getLeader() {
	return leader;
    }

    public void setLeader(Person leader) {
	this.leader = leader;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 71 * hash + this.id;
	hash = 71 * hash + Objects.hashCode(this.name);
	hash = 71 * hash + Objects.hashCode(this.address);
	hash = 71 * hash + Objects.hashCode(this.leader);
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
	final Organization other = (Organization) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	if (!Objects.equals(this.address, other.address)) {
	    return false;
	}
	if (!Objects.equals(this.leader, other.leader)) {
	    return false;
	}
	return true;
    }
}
