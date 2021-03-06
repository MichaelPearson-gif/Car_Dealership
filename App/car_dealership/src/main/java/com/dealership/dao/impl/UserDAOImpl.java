package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.dealership.dao.UserDAO;
import com.dealership.dao.dbutil.PostgresqlConnection;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;

public class UserDAOImpl implements UserDAO{
	
	// Logger variable
	Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public int createUser(User user) throws BusinessException {
		int c = 0;
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dobFormat = Date.valueOf(sdf.format(user.getDob()));
			
			String sql = "INSERT INTO car_dealership.users(username, passwords, users_name, dob) "
					+ "VALUES(?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPasswords());
			preparedStatement.setString(3, user.getUsersname());
			preparedStatement.setDate(4, dobFormat);
			
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("An Internal error has occured. Double check your input for your date of birth or try another user id. If error persists, contact the system admin.");
			
		}

		return c;
	}

	@Override
	public String login(String username) throws BusinessException {
		
		String password = null;
		
try (Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "SELECT passwords FROM car_dealership.users WHERE username = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				password = resultSet.getString("passwords");
				
			}
			
			return password;
			
		}catch (ClassNotFoundException | SQLException e) {
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Internal error occured contact System Admin");
		}
	}

}
