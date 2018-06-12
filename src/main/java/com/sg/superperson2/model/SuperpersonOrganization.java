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
public class SuperpersonOrganization {
    private int id;
    private Superperson superperson;
    private Organization organization;

    public int getId() {
	return id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public Superperson getSuperperson() {
	return superperson;
    }

    public void setSuperperson(Superperson superperson) {
	this.superperson = superperson;
    }

    public Organization getOrganization() {
	return organization;
    }

    public void setOrganization(Organization organization) {
	this.organization = organization;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 73 * hash + this.id;
	hash = 73 * hash + Objects.hashCode(this.superperson);
	hash = 73 * hash + Objects.hashCode(this.organization);
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
	final SuperpersonOrganization other = (SuperpersonOrganization) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.superperson, other.superperson)) {
	    return false;
	}
	if (!Objects.equals(this.organization, other.organization)) {
	    return false;
	}
	return true;
    }
}
