/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.model.Organization;

/**
 *
 * @author main
 */
public interface OrganizationService {
    
    public Organization addOrganization();
    
    public void removeOrganization();
    
    public void updateOrganization();
    
    public List<Organization> getAllOrganizations();
    
    public Organization getOrganizationById(int id);
}
