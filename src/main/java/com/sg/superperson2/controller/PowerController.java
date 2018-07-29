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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String displayPowers(Model model) {
	List<PowerView> powViews = powService.getAllPowerViews();
	model.addAttribute("powers", powViews);
	
	return "powers";
    }
}
