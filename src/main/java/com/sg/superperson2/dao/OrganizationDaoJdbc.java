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

import com.sg.superperson2.model.Location;
import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Superperson;

/**
 *
 * @author main
 */
public class OrganizationDaoJdbc implements OrganizationDao {
    
    // sql
    private final String SQL_INSERT_ORGANIZATION
	    = "insert into organization "
	    + "(name, head, description, location_id) "
	    + "values (?, ?, ?, ?)";
    
    private final String SQL_DELETE_ORGANIZATION
	    = "delete from organization where id = ?";
    
    private final String SQL_UPDATE_ORGANIZATION
	    = "update organization set "
	    + "name = ?, "
	    + "head = ?, "
	    + "description = ?, "
	    + "location_id = ? "
	    + "where id = ?";
    
    private final String SQL_SELECT_ORGANIZATION
	    = "select * from organization where id = ?";
    
    private final String SQL_SELECT_ALL_ORGANIZATIONS
	    = "select * from organization "
	    + "order by name";
    
    private final String SQL_SELECT_ORGANIZATIONS_BY_SUPERPERSON_ID
	    = "select organization.* "
	    + "from organization "
	    + "inner join superperson_organization "
	    + "on organization.id = superperson_organization.organization_id "
	    + "where superperson_organization.superperson_id = ? "
	    + "order by organization.name";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Organization addOrganization(Organization org) {
	
	// empty location check
	Integer locationId;
	if (org.getLocation() != null) {
	    locationId = org.getLocation().getId();
	}
	else {
	    locationId = null;
	}
	
	    jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
		org.getName(),
		org.getHead(),
		org.getDescription(),
		locationId);
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	org.setId(id);
	
	return org;
    }
    
    @Override
    public void removeOrganization(Organization org) {
	jdbcTemplate.update(SQL_DELETE_ORGANIZATION,
		org.getId());
    }
    
    @Override
    public void updateOrganization(Organization org) {
	jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
		org.getName(),
		org.getHead(),
		org.getDescription(),
		org.getLocation().getId(),
		org.getId());
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
	return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
		new OrganizationMapper());
    }
    
    @Override
    public Organization getOrganizationById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
		    new OrganizationMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    @Override
    public List<Organization> getOrganizationsBySuperperson(Superperson sup) {
	return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERPERSON_ID,
		new OrganizationMapper(),
		sup.getId());
    }
    
    private static final class OrganizationMapper
	    implements RowMapper<Organization> {

	@Override
	public Organization mapRow(ResultSet rs, int i)
		throws SQLException {
	    Organization org = new Organization();
	    org.setId(rs.getInt("id"));
	    org.setName(rs.getString("name"));
	    org.setHead(rs.getString("head"));
	    org.setDescription(rs.getString("description"));
	    
	    Location loc = new Location();
	    loc.setId(rs.getInt("location_id"));
	    org.setLocation(loc);
	    
	    return org;
	}
    }
}
