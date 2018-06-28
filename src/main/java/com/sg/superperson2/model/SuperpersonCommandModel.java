/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author main
 */
public class SuperpersonCommandModel {
    private int id;
    private String name;
    private String realName;
    private String description;
    private LocalDate dateOfBirth;
    
    private int organizationId;
    private List<Integer> powerIds;

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

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public LocalDate getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public int getOrganizationId() {
	return organizationId;
    }

    public void setOrganizationId(int organizationId) {
	this.organizationId = organizationId;
    }

    public List<Integer> getPowerIds() {
	return powerIds;
    }

    public void setPowerIds(List<Integer> powerIds) {
	this.powerIds = powerIds;
    }
}
