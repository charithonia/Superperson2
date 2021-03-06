/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author main
 */
public class SuperpersonCommand {
    private int id;
    private String name;
    private String realName;
    private String description;
    private LocalDate dateOfBirth;
    private List<Integer> organizationIds;
    private List<Integer> powerIds;

    public SuperpersonCommand() {
	organizationIds = new ArrayList<>();
	powerIds = new ArrayList<>();
    }
    
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

    public List<Integer> getOrganizationId() {
	return organizationIds;
    }

    public void setOrganizationId(List<Integer> organizationIds) {
	this.organizationIds = organizationIds;
    }

    public List<Integer> getPowerIds() {
	return powerIds;
    }

    public void setPowerIds(List<Integer> powerIds) {
	this.powerIds = powerIds;
    }

    public List<Integer> getOrganizationIds() {
	return organizationIds;
    }

    public void setOrganizationIds(List<Integer> organizationIds) {
	this.organizationIds = organizationIds;
    }
}
