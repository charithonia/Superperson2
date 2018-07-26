/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author main
 */
public class SightingView {
    private int id;
    private LocalDateTime timestamp;
    private Location location;
    private List<Superperson> superpersons;
    private User user;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public LocalDateTime getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
    }

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public List<Superperson> getSuperpersons() {
	return superpersons;
    }

    public void setSuperpersons(List<Superperson> superpersons) {
	this.superpersons = superpersons;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }
}
