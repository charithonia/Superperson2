/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.test.dao;

import java.time.LocalDateTime;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sg.superperson2.dao.UserDao;
import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class UserDaoTest {
    
    @Inject
    UserDao userDao;
    
    // 2 users in database
    private final int N_USERS = 2;
    
    @Test
    @Transactional
    public void testAddGetRemoveUser() {
	
	LocalDateTime now = LocalDateTime.now();
	
	// testing
	User usr1 = new User();
	usr1.setUsername("test_user");
	usr1.setEmail("test@example.com");
	usr1.setDateCreated(now);
	
	User usr2 = new User();
	usr2.setUsername("JohnSmith");
	usr2.setEmail("jsmith@example.com");
	usr2.setDateCreated(now);
	
	userDao.addUser(usr1);
	userDao.addUser(usr2);
	
	User result;
	List<User> resultList;
	
	resultList = userDao.getAllUsers();
	
	assertTrue(resultList.size() == N_USERS + 2);
	
	result = userDao.getUserById(usr1.getId());
	
	assertNotNull(result);
	assertEquals(result.getUsername(), "test_user");
	assertEquals(result.getEmail(), "test@example.com");
	
	result = userDao.getUserById(usr2.getId());
	
	assertNotNull(result);
	assertEquals(result.getUsername(), "JohnSmith");
	assertEquals(result.getEmail(), "jsmith@example.com");
	
	userDao.removeUser(usr1);
	resultList = userDao.getAllUsers();
	
	assertTrue(resultList.size() == N_USERS + 1);
	
	userDao.removeUser(usr2);
	resultList = userDao.getAllUsers();
	
	assertTrue(resultList.size() == N_USERS);
    }
}
