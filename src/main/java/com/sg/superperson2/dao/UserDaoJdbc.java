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

import com.sg.superperson2.model.User;

/**
 *
 * @author main
 */
public class UserDaoJdbc implements UserDao {
    
    // sql
    private final String SQL_INSERT_USER =
	    "insert into user "
	    + "(username, email, date_created) "
	    + "values(?, ?, ?)";
    
    private final String SQL_DELETE_USER =
	    "delete from user where id = ?";
    
    private final String SQL_UPDATE_USER =
	    "update user set "
	    + "username = ?, "
	    + "email = ?, "
	    + "date_created = ? "
	    + "where id = ?";
    
    private final String SQL_SELECT_ALL_USERS =
	    "select * from user";
    
    private final String SQL_SELECT_USER_BY_ID =
	    "select * from user where id = ?";
    
    // jdbc
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public User addUser(User user) {
	Timestamp dateCreated = Timestamp.valueOf(user.getDateCreated());
	
	jdbcTemplate.update(SQL_INSERT_USER,
		user.getUsername(),
		user.getEmail(),
		dateCreated);
	
	int id = jdbcTemplate.queryForObject("select last_insert_id()",
		Integer.class);
	user.setId(id);
	
	return user;
    }
    
    @Override
    public void removeUser(User user) {
	jdbcTemplate.update(SQL_DELETE_USER,
		user.getId());
    }
    
    @Override
    public void updateUser(User user) {
	Timestamp dateCreated = Timestamp.valueOf(user.getDateCreated());
	
	jdbcTemplate.update(SQL_UPDATE_USER,
		user.getUsername(),
		user.getEmail(),
		dateCreated,
		user.getId());
    }
    
    @Override
    public List<User> getAllUsers() {
	return jdbcTemplate.query(SQL_SELECT_ALL_USERS,
		new UserMapper());
    }
    
    @Override
    public User getUserById(int id) {
	try {
	    return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID,
		    new UserMapper(),
		    id);
	}
	catch (EmptyResultDataAccessException ex) {
	    return null;
	}
    }
    
    private final static class UserMapper
	    implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int i)
		throws SQLException {
	    User user = new User();
	    user.setId(rs.getInt("id"));
	    user.setUsername(rs.getString("username"));
	    user.setEmail(rs.getString("email"));
	    
	    Timestamp dateCreated = rs.getTimestamp("date_created");
	    user.setDateCreated(dateCreated.toLocalDateTime());
	    
	    return user;
	}
    }
}
