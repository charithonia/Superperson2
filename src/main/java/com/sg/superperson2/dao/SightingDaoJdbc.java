/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Sighting;
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
	    "select * from sighting";
    
    private final String SQL_SELECT_ALL_SIGHTINGS_ORDER_BY_DATE =
	    "select * from sighting "
	    + "order by timestamp";
    
    private final String SQL_SELECT_SIGHTING_BY_ID =
	    "select * from sighting where id = ?";
    
    //jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Sighting addSighting(Sighting sighting) {
	Timestamp timestamp = Timestamp.valueOf(sighting.getTimestamp());
	
	jdbcTemplate.update(SQL_INSERT_SIGHTING,
		sighting.getLocation().getId(),
		timestamp,
		sighting.getUser().getId());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id",
		Integer.class);
	sighting.setId(id);
	
	return sighting;
    }
    
    @Override
    public void removeSighting(Sighting sighting) {
	jdbcTemplate.update(SQL_DELETE_SIGHTING,
		sighting.getId());
    }
    
    @Override
    public void updateSighting(Sighting sighting) {
	Timestamp timestamp = Timestamp.valueOf(sighting.getTimestamp());
	
	jdbcTemplate.update(SQL_UPDATE_SIGHTING,
		sighting.getLocation().getId(),
		timestamp,
		sighting.getUser().getId(),
		sighting.getId());
    }
    
    @Override
    public List<Sighting> getAllSightings() {
	return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
		new SightingMapper());
    }
    
    @Override
    public List<Sighting> getAllSightingsSortByDate() {
	return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
		new SightingMapper());
    }
    
    @Override
    public Sighting getSightingById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID,
		    new SightingMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
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
