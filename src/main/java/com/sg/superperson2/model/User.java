/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author main
 */
public class User {
    private int id;
    private String email;
    private LocalDateTime dateCreated;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public LocalDateTime getDateCreated() {
	return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
	this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 41 * hash + this.id;
	hash = 41 * hash + Objects.hashCode(this.email);
	hash = 41 * hash + Objects.hashCode(this.dateCreated);
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
	final User other = (User) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.email, other.email)) {
	    return false;
	}
	if (!Objects.equals(this.dateCreated, other.dateCreated)) {
	    return false;
	}
	return true;
    }
}
