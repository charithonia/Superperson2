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
import com.sg.superperson2.model.OrganizationCommand;
import com.sg.superperson2.model.OrganizationView;
import com.sg.superperson2.service.LocationService;
import com.sg.superperson2.service.OrganizationService;

/**
 *
 * @author main
 */
@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    
    @Inject
    LocationService locService;
    
    @Inject
    OrganizationService orgService;
    
    @GetMapping()
    public String organizationsPage(Model model) {
	List<OrganizationView> orgViews = orgService.getAllOrganizationViews();
	model.addAttribute("organizations", orgViews);
	
	return "organizations";
    }
    
    @GetMapping("organization")
    public String organizationPage(HttpServletRequest request, Model model) {
	String idParameter = request.getParameter("id");
	int id = Integer.parseInt(idParameter);
	
	OrganizationView orgView = orgService.getOrganizationViewById(id);
	model.addAttribute("organization", orgView);
	
	return "organization";
    }
    
    @GetMapping("new-organization")
    public String newOrganizationPage(ModelMap modelMap) {
	List<Location> locs = locService.getAllLocations();
	modelMap.addAttribute("locations", locs);
	
	return "new-organization";
    }
    
    @PostMapping("create-organization")
    public String createOrganization(@ModelAttribute("organization") OrganizationCommand orgCom,
	    BindingResult result) {
	try {
	    orgService.addOrganization(orgCom);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    return "new-organization";
	}
	
	return "redirect:/organizations";
    }
    
    @GetMapping("edit-organization")
    public String editOrganizationPage(HttpServletRequest request, ModelMap modelMap) {
	String idParameter = request.getParameter("id");
	int id = Integer.parseInt(idParameter);
	
	List<Location> locs = locService.getAllLocations();
	modelMap.addAttribute("locations", locs);
	
	OrganizationCommand orgCom = orgService.getOrganizationCommandById(id);
	modelMap.addAttribute("organization", orgCom);
	
	return "edit-organization";
    }
    
    @PostMapping("edit-organization")
    public String updateOrganization(@ModelAttribute("organization") OrganizationCommand orgCom,
	    BindingResult result) {
	try {
	    orgService.updateOrganization(orgCom);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    return "redirect:/organizations/edit-organization?id=" + orgCom.getId();
	}
	
	return "redirect:/organizations";
    }
}
