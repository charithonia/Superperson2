/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author main
 */
public class PowerCommand {
    private String message;
    private int id;
    
    @NotEmpty(message = "Name required.")
    @Length(max = 100, message = "Field exceeds maximum length (100).")
    private String name;
    
    @Length (max = 1000, message = "Field exceeds maximum length (1000).")
    private String description;

    public String getMessage() {
	return message;
    }
    
    public void setMessage(String message) {
	this.message = message;
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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
