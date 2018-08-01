/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sg.superperson2.model.SightingView;
import com.sg.superperson2.service.SightingService;

/**
 *
 * @author main
 */
@Controller
@RequestMapping("/sightings")
public class SightingsController {
    
    @Inject
    SightingService sigService;
    
    @GetMapping
    public String displaySightings(Model model) {
	List<SightingView> sigViews = sigService.getAllSightingViews();
	model.addAttribute("sightings", sigViews);
	
	return "sightings";
    }
    
    @PostMapping("/new-sighting")
    
}
