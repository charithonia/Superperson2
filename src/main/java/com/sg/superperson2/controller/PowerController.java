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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.PowerCommand;
import com.sg.superperson2.model.PowerView;
import com.sg.superperson2.service.PowerService;

/**
 *
 * @author main
 */
@Controller
@RequestMapping("/powers")
public class PowerController {
    
    @Inject
    PowerService powService;
    
    @GetMapping
    public String powersPage(Model model) {
	List<PowerView> powViews = powService.getAllPowerViews();
	model.addAttribute("powers", powViews);
	
	return "powers";
    }
    
    @GetMapping("/new-power")
    public String newPowerPage() {
	
	return "new-power";
    }
    
    @PostMapping("/create-power")
    public String createPower(@ModelAttribute("power") PowerCommand powCom,
	    BindingResult result) {
	try {
	    powService.addPower(powCom);
	}
	catch(InvalidObjectException | DuplicateObjectException ex) {
	    return "new-power";
	}
	
	return "redirect:/powers";
    }
    
    @GetMapping("/delete-power")
    public String deletePower(HttpServletRequest request) {
	String idParameter = request.getParameter("id");
	int id = Integer.parseInt(idParameter);
	
	powService.removePowerById(id);
	
	return "redirect:/powers";
    }
    
    @GetMapping("/edit-power")
    public String editPowerPage(HttpServletRequest request, Model model) {
	String idParameter = request.getParameter("id");
	int id = Integer.parseInt(idParameter);
	
	PowerCommand powCom = powService.getPowerCommandById(id);
	model.addAttribute("power", powCom);
	
	return "edit-power";
    }
    
    @PostMapping("/edit-power")
    public String editPower(@ModelAttribute("power") PowerCommand powCom,
	    BindingResult result) {
	try {
	    powService.updatePower(powCom);
	}
	catch (InvalidObjectException | DuplicateObjectException ex) {	    
	    return "/edit-power";
	}
	
	return "redirect:/powers";
    }
}
