/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author main
 */

@Controller
public class ErrorController {
    
    @GetMapping("error")
    public String errorPage(HttpServletRequest request, Model model) {
	String errorMessage = "";
	int httpCode = getHttpCode(request);
	
	switch (httpCode) {
	    case 400: {
		errorMessage = "HTTP 400: Bad request";
		break;
	    }
	    case 401: {
		errorMessage = "HTTP 401: Unauthorized";
		break;
	    }
	    case 404: {
		errorMessage = "HTTP 404: Not found";
		break;
	    }
	    case 500: {
		errorMessage = "HTTP 500: Internal server error";
		break;
	    }
	}
	model.addAttribute("errorMessage", errorMessage);
	
	return "error";
    }
    
    private int getHttpCode(HttpServletRequest request) {
	return (Integer) request
		.getAttribute("javax.servlet.error.status_code");
    }
}
