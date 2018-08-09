/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.OrganizationCommand;
import com.sg.superperson2.model.OrganizationView;

/**
 *
 * @author main
 */
public interface OrganizationService {
    
    public Organization addOrganization(Organization org)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public Organization addOrganization(OrganizationCommand orgCM)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void removeOrganization(Organization org)
	    throws NotFoundException;
    
    public void removeOrganization(OrganizationCommand orgCM)
	    throws NotFoundException;
    
    public void updateOrganization(Organization org)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void updateOrganization(OrganizationCommand orgCom)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public List<Organization> getAllOrganizations();
    
    public Organization getOrganizationById(int id);
    
    public OrganizationCommand getOrganizationCommandById(int id);
    
    public List<OrganizationView> getAllOrganizationViews();
    
    public OrganizationView getOrganizationViewById(int id);
}
