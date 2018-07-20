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

import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Superperson;
import com.sg.superperson2.model.SuperpersonOrganization;

/**
 *
 * @author main
 */
public class SuperpersonOrganizationDaoJdbc
	implements SuperpersonOrganizationDao {
    
    // sql
    private final String SQL_INSERT_SUPERPERSON_ORGANIZATION =
	    "insert into superperson_organization "
	    + "(organization_id, superperson_id) "
	    + "values(?, ?)";
    
    private final String SQL_DELETE_SUPERPERSON_ORGANIZATION =
	    "delete from superperson_organization "
	    + "where id = ?";
    
    private final String SQL_SELECT_ALL_SUPERPERSON_ORGANIZATIONS =
	    "select * from superperson_organization";
    
    private final String SQL_SELECT_SUPERPERSON_ORGANIZATIONS_BY_SUPERPERSON =
	    "select * from superperson_organization where superperson_id = ?";
    
    private final String SQL_SELECT_SUPERPERSON_ORGANIZATION =
	    "select * from superperson_organization where id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public SuperpersonOrganization addSuperpersonOrganization(
	    SuperpersonOrganization supOrg) {
	jdbcTemplate.update(SQL_INSERT_SUPERPERSON_ORGANIZATION,
		supOrg.getOrganization().getId(),
		supOrg.getSuperperson().getId());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	supOrg.setId(id);
	
	return supOrg;
    }
    
    @Override
    public void removeSuperpersonOrganization(SuperpersonOrganization supOrg) {
	jdbcTemplate.update(SQL_DELETE_SUPERPERSON_ORGANIZATION,
		supOrg.getId());
    }
    
    @Override
    public List<SuperpersonOrganization> getAllSuperpersonOrganizations() {
	return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSON_ORGANIZATIONS,
		new SuperpersonOrganizationMapper());
    }
    
    @Override
    public SuperpersonOrganization getSuperpersonOrganizationById(int id) {
	try {
	    return jdbcTemplate.queryForObject(
		    SQL_SELECT_SUPERPERSON_ORGANIZATION,
		    new SuperpersonOrganizationMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<SuperpersonOrganization> getSuperpersonOrganizationsBySuperperson(
	    Superperson sup) {
	return jdbcTemplate.query(
		SQL_SELECT_SUPERPERSON_ORGANIZATIONS_BY_SUPERPERSON,
		new SuperpersonOrganizationMapper(),
		sup.getId());
    }
    
    private final static class SuperpersonOrganizationMapper
	    implements RowMapper<SuperpersonOrganization> {
	
	@Override
	public SuperpersonOrganization mapRow(ResultSet rs, int i)
		throws SQLException {
	    SuperpersonOrganization supOrg = new SuperpersonOrganization();
	    supOrg.setId(rs.getInt("id"));
	    
	    Organization org = new Organization();
	    org.setId(rs.getInt("organization_id"));
	    supOrg.setOrganization(org);
	    
	    Superperson sup = new Superperson();
	    sup.setId(rs.getInt("superperson_id"));
	    supOrg.setSuperperson(sup);
	    
	    return supOrg;
	}
    }
}
