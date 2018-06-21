/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.service;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.User;
import com.sg.superperson2.service.UserService;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class UserServiceTest {
    
    @Inject
    UserService usrService;
    
    @Test
    @Transactional
    public void testInvalidUser()
	    throws DuplicateObjectException {
	
	// No username
	User usr1 = new User();
	usr1.setEmail("test@example.com");
	
	// No email
	User usr2 = new User();
	usr2.setUsername("test_user");
	
	boolean thrown = false;
	try {
	    usrService.addUser(usr1);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
	
	thrown = false;
	try {
	    usrService.addUser(usr2);
	}
	catch (InvalidObjectException ex) {
	    thrown = true;
	}
	
	assertTrue(thrown);
    }
    
    @Test
    @Transactional
    public void testAssignedTimestamp()
	    throws InvalidObjectException, DuplicateObjectException {
	User usr = new User();
	usr.setUsername("test_user");
	usr.setEmail("test@example.com");
	
	User result = usrService.addUser(usr);
	
	boolean hasTimestamp = false;
	if (result.getDateCreated() != null) {
	    hasTimestamp = true;
	}
	
	assertTrue(hasTimestamp);
    }
}
