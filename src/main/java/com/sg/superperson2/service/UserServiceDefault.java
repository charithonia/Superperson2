/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.sg.superperson2.dao.UserDao;
import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
public class UserServiceDefault implements UserService {
    
    @Inject
    UserDao usrDao;
    
    @Override
    public User addUser(User user)
	    throws InvalidObjectException, DuplicateObjectException {
	
	if (!isValid(user)) {
	    throw new InvalidObjectException("User invalid.");
	}
	if (isDuplicate(user)) {
	    throw new DuplicateObjectException("User is a duplicate.");
	}
	
	// Assign a creation timestamp
	LocalDateTime datetime = LocalDateTime.now();
	user.setDateCreated(datetime);
	
	return usrDao.addUser(user);
    }
    
    @Override
    public void removeUser(User user) {
	usrDao.removeUser(user);
    }
    
    @Override
    public void updateUser(User user)
	    throws InvalidObjectException {
	if (!isValid(user)) {
	    throw new InvalidObjectException("User invalid.");
	}
	
	usrDao.updateUser(user);
    }
    
    @Override
    public List<User> getAllUsers() {
	return usrDao.getAllUsers();
    }
    
    @Override
    public User getUserById(int id) {
	return usrDao.getUserById(id);
    }
    
    private boolean isValid(User user) {
	String strField;
	
	strField = user.getUsername();
	if (strField == null || strField.trim().equals("")) {
	    return false;
	}
	
	strField = user.getEmail();
	if (strField == null || strField.trim().equals("")) {
	    return false;
	}
	
	return true;
    }
    
    private boolean isDuplicate(User user) {
	List<User> allUsers = getAllUsers();
	for (User currentUser : allUsers) {
	    if (isMatch(currentUser, user)) {
		return true;
	    }
	}
	return false;
    }
    
    private boolean isMatch(User user1, User user2) {
	if (user1.getUsername().equalsIgnoreCase(user2.getUsername())) {
	    return true;
	}
	else if (user1.getEmail().equalsIgnoreCase(user2.getEmail())) {
	    return true;
	}
	return false;
    }
}
