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

import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public class PowerDaoJdbc implements PowerDao {
    
    // sql
    private final String SQL_INSERT_POWER =
	    "insert into power "
	    + "(name, description) "
	    + "values(?, ?)";
    
    private final String SQL_DELETE_POWER =
	    "delete from power where id = ?";
    
    private final String SQL_UPDATE_POWER =
	    "update power set "
	    + "name = ?, "
	    + "description = ? "
	    + "where id = ?";
    
    private final String SQL_SELECT_ALL_POWERS =
	    "select * from power order by `name`";
    
    private final String SQL_SELECT_POWER_BY_ID =
	    "select * from power where id = ?";
    
    private final String SQL_SELECT_POWERS_BY_SUPERPERSON =
	    "select power.* "
	    + "from power "
	    + "inner join superperson_power "
	    + "on power.id = superperson_power.power_id "
	    + "where superperson_power.superperson_id = 2 "
	    + "order by power.name";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Power addPower(Power power) {
	jdbcTemplate.update(SQL_INSERT_POWER,
		power.getName(),
		power.getDescription());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	power.setId(id);
	
	return power;
    }
    
    @Override
    public void removePower(Power power) {
	jdbcTemplate.update(SQL_DELETE_POWER,
		power.getId());
    }
    
    @Override
    public void updatePower(Power power) {
	jdbcTemplate.update(SQL_UPDATE_POWER,
		power.getName(),
		power.getDescription(),
		power.getId());
    }
    
    @Override
    public List<Power> getAllPowers() {
	return jdbcTemplate.query(SQL_SELECT_ALL_POWERS,
		new PowerMapper());
    }
    
    @Override
    public List<Power> getPowersBySuperperson(Superperson sup) {
	return jdbcTemplate.query(SQL_SELECT_POWERS_BY_SUPERPERSON,
		new PowerMapper());
    }
    
    @Override
    public Power getPowerById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_POWER_BY_ID,
		    new PowerMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    private final static class PowerMapper
	    implements RowMapper<Power> {
	
	@Override
	public Power mapRow(ResultSet rs, int i)
		throws SQLException {
	    Power power = new Power();
	    power.setId(rs.getInt("id"));
	    power.setName(rs.getString("name"));
	    power.setDescription(rs.getString("description"));
	    
	    return power;
	}
    }
}
