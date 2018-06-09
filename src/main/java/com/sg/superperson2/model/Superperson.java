/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author main
 */
public class Superperson {
    private int id;
    private String name;
    private String realName;
    private LocalDate dateOfBirth;
    private String description;

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

    public LocalDate getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 41 * hash + this.id;
	hash = 41 * hash + Objects.hashCode(this.name);
	hash = 41 * hash + Objects.hashCode(this.realName);
	hash = 41 * hash + Objects.hashCode(this.dateOfBirth);
	hash = 41 * hash + Objects.hashCode(this.description);
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
	final Superperson other = (Superperson) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	if (!Objects.equals(this.realName, other.realName)) {
	    return false;
	}
	if (!Objects.equals(this.description, other.description)) {
	    return false;
	}
	if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
	    return false;
	}
	return true;
    }
}