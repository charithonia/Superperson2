/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author main
 */
public class Sighting {
    private int id;
    private Location location;
    private LocalDateTime timestamp;
    
    private List<Superperson> superpersons;
    private User user;

    public Sighting() {
	superpersons = new ArrayList<>();
    }
    
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public LocalDateTime getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
    }

    public List<Superperson> getSuperpersons() {
	return superpersons;
    }

    public void setSuperpersons(List<Superperson> superpersons) {
	this.superpersons.clear();
	for (Superperson superperson : superpersons) {
	    this.superpersons.add(superperson);
	}
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 67 * hash + this.id;
	hash = 67 * hash + Objects.hashCode(this.location);
	hash = 67 * hash + Objects.hashCode(this.timestamp);
	hash = 67 * hash + Objects.hashCode(this.superpersons);
	hash = 67 * hash + Objects.hashCode(this.user);
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
	final Sighting other = (Sighting) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.location, other.location)) {
	    return false;
	}
	if (!Objects.equals(this.timestamp, other.timestamp)) {
	    return false;
	}
	if (!Objects.equals(this.superpersons, other.superpersons)) {
	    return false;
	}
	if (!Objects.equals(this.user, other.user)) {
	    return false;
	}
	return true;
    }
}
