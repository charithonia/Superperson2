/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.SightingCommand;
import com.sg.superperson2.model.SightingView;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.User;
import com.sg.superperson2.service.LocationService;
import com.sg.superperson2.service.SightingService;
import com.sg.superperson2.service.SuperpersonService;
import com.sg.superperson2.service.UserService;

/**
 *
 * @author main
 */
@Controller
@RequestMapping("/sightings")
public class SightingController {
    
    @Inject
    LocationService locService;
    
    @Inject
    SightingService sigService;
    
    @Inject
    SuperpersonService supService;
    
    @Inject
    UserService usrService;
    
    @GetMapping
    public String sightingsPage(Model model) {
	List<SightingView> sigViews = sigService.getAllSightingViews();
	model.addAttribute("sightings", sigViews);
	
	return "sightings";
    }
    
    @GetMapping("sighting")
    public String sightingPage(HttpServletRequest request, Model model) {
	String idParameter = request.getParameter("id");
	int id = Integer.parseInt(idParameter);
	
	SightingView sigView = sigService.getSightingViewById(id);
	model.addAttribute("sighting", sigView);
	
	return "sighting";
    }
    
    @GetMapping("new-sighting")
    public String newSightingPage(ModelMap modelMap) {
	List<Location> locs = locService.getAllLocations();
	modelMap.addAttribute("locations", locs);
	
	List<Superperson> sups = supService.getAllSuperpersons();
	modelMap.addAttribute("superpersons", sups);
	
	List<User> usrs = usrService.getAllUsers();
	modelMap.addAttribute("users", usrs);
	
	return "new-sighting";
    }
    
    @PostMapping("create-sighting")
    public String createSighting(@ModelAttribute("sighting") SightingCommand sigCom,
	    BindingResult result) {
	try {
	    sigService.addSighting(sigCom);
	}
	catch (InvalidObjectException ex) {
	    return "new-sighting";
	}
	
	return "redirect:/sightings";
    }
}
