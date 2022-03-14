package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
	static Connection connection;
	Statement statement;
	
	private static DB db = new DB();
	
	private DB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("[DB] Driver Loaded");
			
			String url = "jdbc:mysql://localhost/estore";
			String user = "root";
			String pass = "Root@1234";
			
			connection = DriverManager.getConnection(url, user, pass);
			System.out.println("[DB] Connection Created");
			
//			statement = (Statement) connection.createStatement();
//			System.out.println("[DB] Statement Created");
			
		} catch (Exception e) {
			System.out.println("Something went wrong "+e);
		}
		
	}
	
	public static DB getDB() {
		return db;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public int executeUpdate(PreparedStatement statement) {
		int result = 0;
		try { // for: insert, update and delete instructions
			result = statement.executeUpdate();
			System.out.println("[DB] SQL Statement Executed");
			
		} catch (Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return result;
	}
	
	public ResultSet executeQuery(PreparedStatement statement) {
		ResultSet set = null; // data structure which will have all the rows
		try { // for: insert, update and delete instructions
			set = statement.executeQuery();
			System.out.println("[DB] SQL Statement Executed");
			
		} catch (Exception e) {
			System.out.println("Something went wrong "+e);
		}
		return set;
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("[DB] Connection Closed");
		} catch (Exception e) {
			System.out.println("Something went wrong "+e);
		}
	}
}

