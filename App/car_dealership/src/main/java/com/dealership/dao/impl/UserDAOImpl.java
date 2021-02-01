package com.dealership.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.dealership.dao.UserDAO;
import com.dealership.dao.dbutil.PostgresqlConnection;
import com.dealership.exceptions.BusinessException;
import com.dealership.model.User;

public class UserDAOImpl implements UserDAO{

	@Override
	public int createUser(User user) throws BusinessException {
		int c = 0;
		try (Connection connection = PostgresqlConnection.getConnection()){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dobFormat = Date.valueOf(sdf.format(user.getDob()));
			
			String sql = "INSERT INTO car_dealership.users(username, passwords, users_name, age, dob) "
					+ "VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPasswords());
			preparedStatement.setString(3, user.getUsersname());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.setDate(5, dobFormat);
			
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
//			throw new BusinessException("An Internal error has occured. Double check your input for your date of birth or try another user id. If error persists, contact the system admin.");
			e.printStackTrace();
			
		}

		return c;
	}

	@Override
	public String login(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
