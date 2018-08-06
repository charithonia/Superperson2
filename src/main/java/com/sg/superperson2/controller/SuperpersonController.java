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
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.SuperpersonCommand;
import com.sg.superperson2.model.SuperpersonView;
import com.sg.superperson2.service.OrganizationService;
import com.sg.superperson2.service.PowerService;
import com.sg.superperson2.service.SuperpersonService;

/**
 *
 * @author main
 */
@Controller
@RequestMapping("/superpeople")
public class SuperpersonController {
    
    @Inject
    OrganizationService orgService;
    
    @Inject
    PowerService powService;
    
    @Inject
    SuperpersonService supService;
    
    @GetMapping()
    public String superpeoplePage(Model model) {
	List<SuperpersonView> supViews = supService.getAllSuperpersonViews();
	model.addAttribute("superpersons", supViews);
	
	return "superpeople";
    }
    
    @GetMapping("/new-superperson")
    public String newSuperpersonPage(ModelMap modelMap) {
	List<Organization> orgs = orgService.getAllOrganizations();
	modelMap.addAttribute("organizations", orgs);
	
	List<Power> pows = powService.getAllPowers();
	modelMap.addAttribute("powers", pows);
	
	return "new-superperson";
    }
    
    @PostMapping("/create-superperson")
    public String createSuperperson(@ModelAttribute("superperson") SuperpersonCommand supCom,
	    BindingResult result) {
	try {
	    supService.addSuperperson(supCom);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {
	    return "new-superperson";
	}
	
	return "redirect:/superpeople";
    }
    
    @GetMapping("/delete-superperson")
    public String deleteSuperperson(HttpServletRequest request) {
	String idParameter = request.getParameter("id");
	int id = Integer.parseInt(idParameter);
	
	supService.removeSuperpersonById(id);
	
	return "redirect:/superpeople";
    }
}
