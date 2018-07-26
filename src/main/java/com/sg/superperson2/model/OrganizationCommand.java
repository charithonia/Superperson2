/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

/**
 *
 * @author main
 */
public class OrganizationCommand {
    private int id;
    private String name;
    private String head;
    private String description;
    
    private int locationId;

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

    public int getLocationId() {
	return locationId;
    }

    public void setLocationId(int locationId) {
	this.locationId = locationId;
    }
}
