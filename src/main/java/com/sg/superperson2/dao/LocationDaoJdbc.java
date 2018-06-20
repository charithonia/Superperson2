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

import com.sg.superperson2.model.Address;
import com.sg.superperson2.model.Location;

/**
 *
 * @author main
 */
public class LocationDaoJdbc implements LocationDao {
    
    // sql
    private final String SQL_INSERT_LOCATION
	    = "insert into location "
	    + "(latitude, longitude, name, address_id) "
	    + "values(?, ?, ?, ?)";
    
    private final String SQL_DELETE_LOCATION
	    = "delete from location where id = ?";
    
    private final String SQL_UPDATE_LOCATION
	    = "update location set "
	    + "latitude = ?, "
	    + "longitude = ?, "
	    + "name = ?, "
	    + "address_id = ? "
	    + "where id = ?";
    
    private final String SQL_SELECT_LOCATION
	    = "select * from location where id = ?";
    
    private final String SQL_SELECT_ALL_LOCATIONS
	    = "select * from location";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private AddressDao addressDao;
    
    public void setAddressDao(AddressDao addressDao) {
	this.addressDao = addressDao;
    }
    
    @Override
    public Location addLocation(Location location) {
	
	// empty address check
	Integer addressId;
	if (location.getAddress() != null) {
	    addressId = location.getAddress().getId();
	}
	else {
	    addressId = null;
	}
	
	jdbcTemplate.update(SQL_INSERT_LOCATION,
		location.getLatitude(),
		location.getLongitude(),
		location.getName(),
		addressId);
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	location.setId(id);
	
	return location;
    }
    
    @Override
    public void removeLocation(Location location) {
	jdbcTemplate.update(SQL_DELETE_LOCATION,
		location.getId());
    }
    
    @Override
    public void updateLocation(Location location) {
	jdbcTemplate.update(SQL_UPDATE_LOCATION,
		location.getLongitude(),
		location.getLatitude(),
		location.getName(),
		location.getAddress().getId(),
		location.getId());
    }
    
    @Override
    public List<Location> getAllLocations() {
	return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
		new LocationMapper());
    }

    @Override
    public Location getLocationById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
		    new LocationMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    private static final class LocationMapper
	    implements RowMapper<Location> {

	@Override
	public Location mapRow(ResultSet rs, int i)
		throws SQLException {
	    Location location = new Location();
	    location.setId(rs.getInt("id"));
	    location.setLatitude(rs.getDouble("latitude"));
	    location.setLongitude(rs.getDouble("longitude"));
	    location.setName(rs.getString("name"));
	    
	    Address address = new Address();
	    address.setId(rs.getInt("address_id"));
	    location.setAddress(address);
	    
	    return location;
	}
    }
}
