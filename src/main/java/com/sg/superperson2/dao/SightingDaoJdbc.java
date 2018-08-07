/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.SightingSuperperson;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
public class SightingDaoJdbc implements SightingDao {
    
    // sql
    private final String SQL_INSERT_SIGHTING =
	    "insert into sighting "
	    + "(location_id, timestamp, user_id) "
	    + "values(?, ?, ?)";
    
    private final String SQL_DELETE_SIGHTING =
	    "delete from sighting where id = ?";
    
    private final String SQL_UPDATE_SIGHTING =
	    "update sighting set "
	    + "location_id = ?, "
	    + "timestamp = ?, "
	    + "user_id = ? "
	    + "where id = ?";
    
    private final String SQL_SELECT_ALL_SIGHTINGS =
	    "select * from sighting order by timestamp desc";
    
    private final String SQL_SELECT_ALL_SIGHTINGS_LIMIT_OFFSET =
	    "select * from sighting order by timestamp desc"
	    + "limit ? offset ?";
    
    private final String SQL_SELECT_SIGHTING_BY_ID =
	    "select * from sighting where id = ?";
    
    //jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Inject
    SightingSuperpersonDao sigSupDao;
    
    @Override
    public Sighting addSighting(Sighting sig) {
	Timestamp timestamp = Timestamp.valueOf(sig.getTimestamp());
	
	jdbcTemplate.update(SQL_INSERT_SIGHTING,
		sig.getLocation().getId(),
		timestamp,
		sig.getUser().getId());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	sig.setId(id);
	
	// Superperson bridges
	List<Superperson> sups = sig.getSuperpersons();
	for (Superperson sup : sups) {
	    SightingSuperperson sigSup = new SightingSuperperson();
	    sigSup.setSighting(sig);
	    sigSup.setSuperperson(sup);
	    
	    sigSupDao.addSightingSuperperson(sigSup);
	}
	
	appendData(sig);
	return sig;
    }
    
    @Override
    public void removeSighting(Sighting sig) {
	jdbcTemplate.update(SQL_DELETE_SIGHTING,
		sig.getId());
    }
    
    @Override
    public void updateSighting(Sighting sig) {
	Timestamp timestamp = Timestamp.valueOf(sig.getTimestamp());
	
	jdbcTemplate.update(SQL_UPDATE_SIGHTING,
		sig.getLocation().getId(),
		timestamp,
		sig.getUser().getId(),
		sig.getId());
	
	// Rebuild bridges
	List<SightingSuperperson> sigSups = sigSupDao
		.getSightingSuperpersonsBySighting(sig);
	for (SightingSuperperson sigSup : sigSups) {
	    sigSupDao.removeSightingSuperperson(sigSup);
	}
	
	List<Superperson> sups = sig.getSuperpersons();
	for (Superperson sup : sups) {
	    SightingSuperperson sigSup = new SightingSuperperson();
	    sigSup.setSighting(sig);
	    sigSup.setSuperperson(sup);
	    
	    sigSupDao.addSightingSuperperson(sigSup);
	}
    }
    
    @Override
    public List<Sighting> getAllSightings() {
	List<Sighting> sigs = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
		new SightingMapper());
	
	for (Sighting sig : sigs) {
	    appendData(sig);
	}
	
	return sigs;
    }
    
    @Override
    public List<Sighting> getSightings(int limit) {
	List<Sighting> sigs = jdbcTemplate.query(
		SQL_SELECT_ALL_SIGHTINGS_LIMIT_OFFSET,
		new SightingMapper(),
		limit, 0);
	
	for (Sighting sig : sigs) {
	    appendData(sig);
	}
	
	return sigs;
    }
    
    @Override
    public Sighting getSightingById(int id) {
	try {
	    Sighting sig = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID,
		    new SightingMapper(),
		    id);
	    appendData(sig);
	    
	    return sig;
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    /**
     * This method completes a Sighting object by referencing bridge tables.
     * @param sig The Sighting requiring data
     * @return The completed Sighting object
     */
    private Sighting appendData(Sighting sig) {
	
	// Load superperson ids
	List<SightingSuperperson> sigSups = sigSupDao
		.getSightingSuperpersonsBySighting(sig);
	List<Superperson> sups = new ArrayList<>();
	for (SightingSuperperson sigSup : sigSups) {
	    Superperson sup = new Superperson();
	    sup.setId(sigSup.getSuperperson().getId());
	    
	    sups.add(sup);
	}
	sig.setSuperpersons(sups);
	
	return sig;
    }
    
    private static final class SightingMapper
	    implements RowMapper<Sighting> {
	
	@Override
	public Sighting mapRow(ResultSet rs, int i)
		throws SQLException {
	    Sighting sighting = new Sighting();
	    sighting.setId(rs.getInt("id"));
	    
	    Location loc = new Location();
	    loc.setId(rs.getInt("location_id"));
	    sighting.setLocation(loc);
	    
	    Timestamp timestamp = rs.getTimestamp("timestamp");
	    if (timestamp != null) {
		sighting.setTimestamp(timestamp.toLocalDateTime());
	    }
	    
	    User user = new User();
	    user.setId(rs.getInt("user_id"));
	    sighting.setUser(user);
	    
	    return sighting;
	}
    }
}
