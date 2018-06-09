/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.superperson2.model.Sighting;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public class SuperpersonDaoJdbc implements SuperpersonDao {
    
    // sql
    private final String SQL_INSERT_SUPERPERSON =
	    "insert into superperson "
	    + "(name, real_name, date_of_birth, description) "
	    + "values(?, ?, ?, ?)";
    
    private final String SQL_DELETE_SUPERPERSON =
	    "delete from superperson where id = ?";
    
    private final String SQL_UPDATE_SUPERPERSON =
	    "update superperson set "
	    + "name = ?, "
	    + "real_name = ?, "
	    + "date_of_birth = ?, "
	    + "description = ? "
	    + "where id = ?";
    
    private final String SQL_SELECT_ALL_SUPERPERSONS =
	    "select * from superperson";
    
    private final String SQL_SELECT_SUPERPERSON_BY_ID =
	    "select * from superperson where id = ?";
    
    private final String SQL_SELECT_SUPERPERSONS_BY_SIGHTING =
	    "select superperson.* from superperson "
	    + "inner join sighting_superperson "
	    + "on superperson.id = sighting_superperson.superperson_id "
	    + "where sighting_superperson.sighting_id = ? "
	    + "order by superperson.name";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Superperson addSuperperson(Superperson superperson) {
	Date dob = Date.valueOf(superperson.getDateOfBirth());
	
	jdbcTemplate.update(SQL_INSERT_SUPERPERSON,
		superperson.getName(),
		superperson.getRealName(),
		dob,
		superperson.getDescription());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	superperson.setId(id);
	
	return superperson;
    }
    
    @Override
    public void removeSuperperson(Superperson superperson) {
	jdbcTemplate.update(SQL_DELETE_SUPERPERSON,
		superperson.getId());
    }
    
    @Override
    public void updateSuperperson(Superperson superperson) {
	Date dob = Date.valueOf(superperson.getDateOfBirth());
	
	jdbcTemplate.update(SQL_UPDATE_SUPERPERSON,
		superperson.getName(),
		superperson.getRealName(),
		dob,
		superperson.getDescription(),
		superperson.getId());
    }
    
    @Override
    public List<Superperson> getAllSuperpersons() {
	return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSONS,
		new SuperpersonMapper());
    }
    
    @Override
    public Superperson getSuperpersonById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON_BY_ID,
		    new SuperpersonMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<Superperson> getSuperpersonsBySighting(Sighting sighting) {
	return jdbcTemplate.query(SQL_SELECT_SUPERPERSONS_BY_SIGHTING,
		new SuperpersonMapper(),
		sighting.getId());
    }
    
    private static final class SuperpersonMapper
	    implements RowMapper<Superperson> {
	
	@Override
	public Superperson mapRow(ResultSet rs, int i)
		throws SQLException {
	    Superperson sup = new Superperson();
	    sup.setId(rs.getInt("id"));
	    sup.setName(rs.getString("name"));
	    sup.setRealName(rs.getString("real_name"));
	    
	    Date dob = rs.getDate("date_of_birth");
	    if (dob != null) {
		sup.setDateOfBirth(dob.toLocalDate());
	    }
	    
	    sup.setDescription(rs.getString("description"));
	    
	    return sup;
	}
    }
}
