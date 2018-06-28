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
public class SightingCommandModel {
    private int id;
    private LocalDateTime timestamp;
    
    private int userId;
    private List<Integer> superpersonIds;

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

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public List<Integer> getSuperpersonIds() {
	return superpersonIds;
    }

    public void setSuperpersonIds(List<Integer> superpersonIds) {
	this.superpersonIds = superpersonIds;
    }
}
