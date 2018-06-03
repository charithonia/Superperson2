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
    private String head;
    private String description;
    
    private Location location;

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

    public String getHead() {
	return head;
    }

    public void setHead(String head) {
	this.head = head;
    }
    
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
    
    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 83 * hash + this.id;
	hash = 83 * hash + Objects.hashCode(this.name);
	hash = 83 * hash + Objects.hashCode(this.head);
	hash = 83 * hash + Objects.hashCode(this.description);
	hash = 83 * hash + Objects.hashCode(this.location);
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
	if (!Objects.equals(this.head, other.head)) {
	    return false;
	}
	if (!Objects.equals(this.description, other.description)) {
	    return false;
	}
	if (!Objects.equals(this.location, other.location)) {
	    return false;
	}
	return true;
    }
}
