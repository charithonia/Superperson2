/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.exception.*;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonCommand;
import com.sg.superperson2.model.SuperpersonView;

/**
 *
 * @author main
 */
public interface SuperpersonService {
    
    public Superperson addSuperperson(Superperson superperson)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public Superperson addSuperperson(SuperpersonCommand superpersonCommand)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void removeSuperperson(Superperson superperson);
    
    public void removeSuperpersonById(int id);
    
    public void updateSuperperson(Superperson superperson)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public void updateSuperperson(SuperpersonCommand superpersonCommand)
	    throws InvalidObjectException, DuplicateObjectException;
    
    public List<Superperson> getAllSuperpersons();
    
    public Superperson getSuperpersonById(int id);
    
    public List<SuperpersonView> getAllSuperpersonViews();
    
    public SuperpersonView getSuperpersonViewById(int id);
}
