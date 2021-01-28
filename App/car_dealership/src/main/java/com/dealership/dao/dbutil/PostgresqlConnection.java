package com.dealership.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {
	
	private static Connection connection;

	private PostgresqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username1 = "postgres";
		String password = "trxr4kidz";
		connection = DriverManager.getConnection(url, username1, password);
		return connection;
	}
}
