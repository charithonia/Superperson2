/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.util.List;

/**
 *
 * @author main
 */
public class SuperpersonView {
    private int id;
    private String name;
    private String description;
    private List<Organization> organizations;
    private List<Power> powers;

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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    
    public List<Organization> getOrganizations() {
	return organizations;
    }
    
    public void setOrganizations(List<Organization> organizations) {
	this.organizations = organizations;
    }
    
    public List<Power> getPowers() {
	return powers;
    }

    public void setPowers(List<Power> powers) {
	this.powers = powers;
    }
}
