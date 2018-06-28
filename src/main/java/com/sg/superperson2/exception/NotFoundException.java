/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.exception;

/**
 *
 * @author main
 */
public class NotFoundException extends Exception {
    public NotFoundException() {
	super();
    }
    
    public NotFoundException(String message) {
	super(message);
    }
}
