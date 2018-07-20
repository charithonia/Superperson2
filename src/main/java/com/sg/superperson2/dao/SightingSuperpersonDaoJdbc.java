/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingSuperperson;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public class SightingSuperpersonDaoJdbc implements SightingSuperpersonDao {
    
    // sql
    private final String SQL_INSERT_SIGHTING_SUPERPERSON =
	    "insert into sighting_superperson "
	    + "(sighting_id, superperson_id) "
	    + "values(?, ?)";
    
    private final String SQL_DELETE_SIGHTING_SUPERPERSON =
	    "delete from sighting_superperson "
	    + "where id = ?";
    
    private final String SQL_SELECT_ALL_SIGHTING_SUPERPERSONS =
	    "select * from sighting_superperson";
    
    private final String SQL_SELECT_SIGHTING_SUPERPERSON_BY_ID =
	    "select * from sighting_superperson where id = ?";
    
    private final String SQL_SELECT_SIGHTING_SUPERPERSON_BY_SUPERPERSON =
	    "select * from sighting_superperson where sighting_id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public SightingSuperperson addSightingSuperperson(
	    SightingSuperperson sigSup) {
	jdbcTemplate.update(SQL_INSERT_SIGHTING_SUPERPERSON,
		sigSup.getSighting().getId(),
		sigSup.getSuperperson().getId());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	sigSup.setId(id);
	
	return sigSup;
    }
    
    @Override
    public void removeSightingSuperperson(SightingSuperperson sigSup) {
	jdbcTemplate.update(SQL_DELETE_SIGHTING_SUPERPERSON,
		sigSup.getId());
    }
    
    @Override
    public List<SightingSuperperson> getAllSightingSuperpersons() {
	return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING_SUPERPERSONS,
		new SightingSuperpersonMapper());
    }
    
    @Override
    public SightingSuperperson getSightingSuperpersonById(int id) {
	try {
	    return jdbcTemplate.queryForObject(
		    SQL_SELECT_SIGHTING_SUPERPERSON_BY_ID,
		    new SightingSuperpersonMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<SightingSuperperson> getSightingSuperpersonsBySighting(
	    Sighting sig) {
	return jdbcTemplate.query(SQL_SELECT_SIGHTING_SUPERPERSON_BY_SUPERPERSON,
		new SightingSuperpersonMapper(),
		sig.getId());
    }
    
    private final static class SightingSuperpersonMapper
	    implements RowMapper<SightingSuperperson> {
	
	@Override
	public SightingSuperperson mapRow(ResultSet rs, int i)
		throws SQLException {
	    SightingSuperperson sigSup = new SightingSuperperson();
	    sigSup.setId(rs.getInt("id"));
	    
	    Sighting sighting = new Sighting();
	    sighting.setId(rs.getInt("sighting_id"));
	    sigSup.setSighting(sighting);
	    
	    Superperson sup = new Superperson();
	    sup.setId(rs.getInt("superperson_id"));
	    sigSup.setSuperperson(sup);
	    
	    return sigSup;
	}
    }
}
