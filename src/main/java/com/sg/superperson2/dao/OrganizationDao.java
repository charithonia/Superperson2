/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public interface OrganizationDao {
    
    public Organization addOrganization(Organization organization);
    
    public void removeOrganization(Organization organization);
    
    public void updateOrganization(Organization organization);
    
    public List<Organization> getAllOrganizations();
    
    public Organization getOrganizationById(int id);
        
    public List<Organization> getOrganizationsBySuperperson(
	    Superperson superperson);
}
