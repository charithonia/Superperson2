/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.superperson2.model.Organization;
import com.sg.superperson2.model.Power;
import com.sg.superperson2.model.SuperpersonOrganization;
import com.sg.superperson2.model.SuperpersonPower;
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
    
    @Inject
    OrganizationDao orgDao;
    
    @Inject
    PowerDao powDao;
    
    @Inject
    SuperpersonOrganizationDao supOrgDao;
    
    @Inject
    SuperpersonPowerDao supPowDao;
    
    @Override
    public Superperson addSuperperson(Superperson sup) {
	Date dob;
	if (sup.getDateOfBirth() != null) {
	    dob = Date.valueOf(sup.getDateOfBirth());
	}
	else {
	    dob = null;
	}
	
	jdbcTemplate.update(SQL_INSERT_SUPERPERSON,
		sup.getName(),
		sup.getRealName(),
		dob,
		sup.getDescription());
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	sup.setId(id);
	
	// Organization bridges
	List<Organization> orgs = sup.getOrganizations();
	for (Organization org : orgs) {
	    SuperpersonOrganization supOrg = new SuperpersonOrganization();
	    supOrg.setOrganization(org);
	    supOrg.setSuperperson(sup);
	    
	    supOrgDao.addSuperpersonOrganization(supOrg);
	}
	
	// Power bridges
	List<Power> pows = sup.getPowers();
	for (Power pow : pows) {
	    SuperpersonPower supPow = new SuperpersonPower();
	    supPow.setPower(pow);
	    supPow.setSuperperson(sup);
	    
	    supPowDao.addSuperpersonPower(supPow);
	}
	
	return sup;
    }
    
    @Override
    public void removeSuperperson(Superperson sup) {
	jdbcTemplate.update(SQL_DELETE_SUPERPERSON,
		sup.getId());
    }
    
    @Override
    public void updateSuperperson(Superperson sup) {
	Date dob = Date.valueOf(sup.getDateOfBirth());
	
	jdbcTemplate.update(SQL_UPDATE_SUPERPERSON,
		sup.getName(),
		sup.getRealName(),
		dob,
		sup.getDescription(),
		sup.getId());
	
	// Rebuild bridges
	List<SuperpersonOrganization> supOrgs = supOrgDao
		.getSuperpersonOrganizationsBySuperperson(sup);
	for (SuperpersonOrganization supOrg : supOrgs) {
	    supOrgDao.removeSuperpersonOrganization(supOrg);
	}
	
	List<Organization> orgs = sup.getOrganizations();
	for (Organization org : orgs) {
	    SuperpersonOrganization supOrg = new SuperpersonOrganization();
	    supOrg.setOrganization(org);
	    supOrg.setSuperperson(sup);
	    
	    supOrgDao.addSuperpersonOrganization(supOrg);
	}
	
	List<SuperpersonPower> supPows = supPowDao
		.getSuperpersonPowersBySuperperson(sup);
	for (SuperpersonPower supPow : supPows) {
	    supPowDao.removeSuperpersonPower(supPow);
	}
	
	List<Power> pows = sup.getPowers();
	for (Power pow : pows) {
	    SuperpersonPower supPow = new SuperpersonPower();
	    supPow.setPower(pow);
	    supPow.setSuperperson(sup);
	    
	    supPowDao.addSuperpersonPower(supPow);
	}
    }
    
    @Override
    public List<Superperson> getAllSuperpersons() {
	List<Superperson> sups = jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSONS,
		new SuperpersonMapper());
	for (Superperson sup : sups) {
	    appendData(sup);
	}
	
	return sups;
    }
    
    @Override
    public Superperson getSuperpersonById(int id) {
	try {
	    Superperson sup = jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON_BY_ID,
		    new SuperpersonMapper(),
		    id);
	    appendData(sup);
	    
	    return sup;
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
    
    /**
     * This method completes a Superperson object by referencing bridge tables.
     * @param sup The Superperson to be completed
     * @return The completed Superperson
     */
    private Superperson appendData(Superperson sup) {
	
	// Load organization ids
	List<SuperpersonOrganization> supOrgs = supOrgDao
		.getSuperpersonOrganizationsBySuperperson(sup);
	List<Organization> orgs = new ArrayList<>();
	for (SuperpersonOrganization supOrg : supOrgs) {
	    Organization org = new Organization();
	    org.setId(supOrg.getOrganization().getId());
	    
	    orgs.add(org);
	}
	sup.setOrganizations(orgs);
	
	// Load power ids
	List<SuperpersonPower> supPows = supPowDao
		.getSuperpersonPowersBySuperperson(sup);
	List<Power> pows = new ArrayList<>();
	for (SuperpersonPower supPow : supPows) {
	    Power pow = new Power();
	    pow.setId(supPow.getPower().getId());
	    
	    pows.add(pow);
	}
	sup.setPowers(pows);
	
	return sup;
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
