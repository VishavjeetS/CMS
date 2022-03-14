package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.example.Model.Customer;

public class CustomerDAO implements DAO<Customer>{

	DB db = DB.getDB();
	
	@Override
	public int insert(Customer object) {
		int result = 0;
		String sql = "insert into customer values(null, ?, ?, ?, ?, ?)";
		try {
			Connection connection = db.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, object.name);
			statement.setString(2, object.phone);
			statement.setString(3, object.email);
			statement.setInt(4, object.rewardPoints);
			statement.setString(5, object.registeredOnDate);
			result = db.executeUpdate(statement);
		} catch (SQLException e) {	
			System.err.println("Something went wrong "+e);	
		}
		return result;
	}

	@Override
	public int update(Customer object) {
		int result = 0;
		String sql = "update customer set name = ?, phone = ?, email = ?, rewardPoints = ? where cid = ?";
		try {
			Connection connection = db.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, object.name);
			statement.setString(2, object.phone);
			statement.setString(3, object.email);
			statement.setInt(4, object.rewardPoints);
			statement.setInt(5, object.cid);
			result = db.executeUpdate(statement);
		} catch (SQLException e) {	
			System.err.println("Something went wrong "+e);
		}
		return result;
	}

	@Override
	public int delete(int id) {
		int result = 0;
		String sql = "delete from customer where cid = ?";
		try {
			Connection connection = db.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			result = db.executeUpdate(statement);
		} catch (SQLException e) {	
			System.err.println("Something went wrong "+e);	
		}
		return result;
	}

	@Override
	public ArrayList<Customer> query() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql = "select * from customer";
		try {
			Connection connection = db.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			if(set!=null) {
				while(set.next()) { // If there exist data or next row
					Customer customer = new Customer();
					customer.cid = set.getInt("cid");
					customer.name = set.getString("name");
					customer.phone = set.getString("phone");
					customer.email = set.getString("email");
					customer.rewardPoints = set.getInt("rewardPoints");
					customer.registeredOnDate = set.getString("registeredOnDate");
					customers.add(customer);
				}
			}
		} catch (SQLException e) {	
			System.err.println("Something went wrong "+e);	
		}
		return customers;
	}

}
