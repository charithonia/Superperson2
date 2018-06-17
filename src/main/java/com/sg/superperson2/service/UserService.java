/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
public interface UserService {
    
    public User addUser(User user);
    
    public void removeUser(User user);
    
    public void updateUser(User user);
    
    public List<User> getAllUsers();
    
    public User getUserById(int id);
}
