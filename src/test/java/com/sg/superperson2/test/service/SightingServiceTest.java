/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingView;
import com.sg.superperson2.service.SightingService;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class SightingServiceTest {
    
    @Inject
    SightingService sigService;
    
    @Test
    @Transactional
    public void testInvalidSighting() {
	Sighting sig = new Sighting();
	
	boolean thrown = false;
	try {
	    sigService.addSighting(sig);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testGetSightingViews() {
	
	// This shouldn't be empty. If it is, problems.
	List<SightingView> sigViews = sigService.getAllSightingViews();
	
	assertFalse(sigViews.isEmpty());
    }
}