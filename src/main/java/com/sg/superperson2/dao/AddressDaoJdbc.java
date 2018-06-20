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

/**
 *
 * @author main
 */
public class AddressDaoJdbc implements AddressDao {
    
    // sql
    private final String SQL_INSERT_ADDRESS =
	    "insert into address "
	    + "(number, street, city, state, zip) "
	    + "values(?, ?, ?, ?, ?)";
    
    private final String SQL_DELETE_ADDRESS =
	    "delete from address where id = ?";
    
    private final String SQL_UPDATE_ADDRESS = "";
    
    private final String SQL_SELECT_ADDRESS =
	    "select * from address where id = ?";
    
    private final String SQL_SELECT_ALL_ADDRESSES =
	    "select * from address";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Address addAddress(Address address) {
	jdbcTemplate.update(SQL_INSERT_ADDRESS,
		address.getNumber(),
		address.getStreet(),
		address.getCity(),
		address.getState(),
		address.getZip());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	address.setId(id);
	
	return address;
    }
    
    @Override
    public void removeAddress(Address address) {
	jdbcTemplate.update(SQL_DELETE_ADDRESS,
		address.getId());
    }
    
    @Override
    public void updateAddress(Address address) {
	
    }
    
    @Override
    public List<Address> getAllAddresses() {
	return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES,
		new AddressMapper());
    }

    @Override
    public Address getAddressById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
		    new AddressMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    private static final class AddressMapper
	    implements RowMapper<Address> {
	
	@Override
	public Address mapRow(ResultSet rs, int i)
		throws SQLException{
	    Address address = new Address();
	    address.setId(rs.getInt("id"));
	    address.setNumber(rs.getString("number"));
	    address.setStreet(rs.getString("street"));
	    address.setCity(rs.getString("city"));
	    address.setState(rs.getString("state"));
	    address.setZip(rs.getString("zip"));
	    
	    return address;
	}
    }
}
