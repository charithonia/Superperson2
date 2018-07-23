/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.controller;

import java.util.Map;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sg.superperson2.model.SightingCommandModel;
import com.sg.superperson2.service.SightingService;

/**
 *
 * @author main
 */
@Controller
@RequestMapping({"/"})
public class IndexController {
    
    @Inject
    SightingService sigService;
    
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
	
	// Get all sightings to display on homepage
	List<SightingCommandModel> sigCMs = sigService.getAllSightingCommands();
	return "/";
    }
}
