/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.util.List;

import com.sg.superperson2.model.SuperpersonOrganization;

/**
 *
 * @author main
 */
public interface SuperpersonOrganizationDao {
    
    public SuperpersonOrganization addSuperpersonOrganization(
	    SuperpersonOrganization superpersonOrganization);
    
    public void removeSuperpersonOrganization(SuperpersonOrganization
	    superpersonOrganization);
    
    public List<SuperpersonOrganization> getAllSuperpersonOrganizations();
    
    public SuperpersonOrganization getSuperpersonOrganizationById(int id);
}
