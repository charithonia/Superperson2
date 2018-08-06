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
public class ErrorView {
    private String message;
    private String stackTrace;
    private int objectId;
    private String url;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getStackTrace() {
	return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
	this.stackTrace = stackTrace;
    }

    public int getObjectId() {
	return objectId;
    }

    public void setObjectId(int objectId) {
	this.objectId = objectId;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }
}
