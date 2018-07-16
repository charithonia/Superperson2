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
public class SightingViewModel {
    private List<String> superpersons;
    private LocalDateTime timestamp;
    private String location;
    private String user;

    public List<String> getSuperpersons() {
	return superpersons;
    }

    public void setSuperpersons(List<String> superpersons) {
	this.superpersons = superpersons;
    }

    public LocalDateTime getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }
}
