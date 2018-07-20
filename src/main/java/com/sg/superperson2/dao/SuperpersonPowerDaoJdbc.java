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
import com.sg.superperson2.model.SuperpersonPower;

/**
 *
 * @author main
 */
public class SuperpersonPowerDaoJdbc implements SuperpersonPowerDao {
    
    // sql
    private final String SQL_INSERT_SUPERPERSON_POWER =
	    "insert into superperson_power "
	    + "(power_id, superperson_id) "
	    + "values(?, ?)";
    
    private final String SQL_DELETE_SUPERPERSON_POWER =
	    "delete from superperson_power where id = ?";
    
    private final String SQL_SELECT_ALL_SUPERPERSON_POWERS =
	    "select * from superperson_power";
    
    private final String SQL_SELECT_SUPERPERSON_POWER =
	    "select * from superperson_power where id = ?";
    
    private final String SQL_SELECT_SUPERPERSON_POWERS_BY_SUPERPERSON =
	    "select * from superperson_power where superperson_id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public SuperpersonPower addSuperpersonPower(SuperpersonPower supPow) {
	jdbcTemplate.update(SQL_INSERT_SUPERPERSON_POWER,
		supPow.getPower().getId(),
		supPow.getSuperperson().getId());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	supPow.setId(id);
	
	return supPow;
    }
    
    @Override
    public void removeSuperpersonPower(SuperpersonPower supPow) {
	jdbcTemplate.update(SQL_DELETE_SUPERPERSON_POWER,
		supPow.getId());
    }
    
    @Override
    public List<SuperpersonPower> getAllSuperpersonPowers() {
	return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSON_POWERS,
		new SuperpersonPowerMapper());
    }
    
    @Override
    public SuperpersonPower getSuperpersonPowerById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON_POWER,
		    new SuperpersonPowerMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<SuperpersonPower> getSuperpersonPowersBySuperperson(
	    Superperson sup) {
	return jdbcTemplate.query(
		SQL_SELECT_SUPERPERSON_POWERS_BY_SUPERPERSON,
		new SuperpersonPowerMapper(),
		sup.getId());
    }
    
    private final static class SuperpersonPowerMapper
	    implements RowMapper<SuperpersonPower> {
	
	@Override
	public SuperpersonPower mapRow(ResultSet rs, int i)
		throws SQLException {
	    SuperpersonPower supPow = new SuperpersonPower();
	    supPow.setId(rs.getInt("id"));
	    
	    Power pow = new Power();
	    pow.setId(rs.getInt("power_id"));
	    supPow.setPower(pow);
	    
	    Superperson sup = new Superperson();
	    sup.setId(rs.getInt("superperson_id"));
	    supPow.setSuperperson(sup);
	    
	    return supPow;
	}
    }
}
