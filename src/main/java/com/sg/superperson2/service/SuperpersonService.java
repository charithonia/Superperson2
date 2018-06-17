/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.service;

import java.util.List;

import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public interface SuperpersonService {
    
    public Superperson addSuperperson(Superperson superperson);
    
    public void removeSuperperson(Superperson superperson);
    
    public void updateSuperperson(Superperson superperson);
    
    public List<Superperson> getAllSuperpersons();
    
    public Superperson getSuperpersonById();
}
