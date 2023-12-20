package edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.model.Customer;
import edu.neu.csye6200.repository.DatabaseConnection;

public class CustomerDaoImpl implements CustomerDao {
	Connection connection = DatabaseConnection.getDbInstance();
	
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers = new ArrayList<Customer>();
		Statement statement;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String sqlQuery = "select * from customer";
			rs = statement.executeQuery(sqlQuery);
			
			while(rs.next()) {
				int customerId = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");		
				allCustomers.add(new Customer(customerId, email, password, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return allCustomers;
	}

	@Override
	public Customer getCustomerById(int id) {
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from customer where id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int customerId = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");		
				return new Customer(customerId, email, password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void addCustomer(Customer customer) {
		try {
			String sqlQuery = "INSERT INTO CUSTOMER (email, password, name) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
	        
			ps.setString(1, customer.getEmail());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getName());
			int customerAdded = ps.executeUpdate();
			
            System.out.println("execute boolean output = " + customerAdded + "\n");			
			if (customerAdded > 0) {
				System.out.println("Added customer to database");
			} else {
				System.out.println("Error adding customer " + customer.getEmail());
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateCustomer(Customer customer) {
		try {
			String sqlQuery = "UPDATE customer " + 
		            "SET email= ?, password = ?, name = ?"
					+ " WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setString(1,customer.getEmail());
			ps.setString(2,customer.getPassword());
			ps.setString(3, customer.getName());
			ps.setInt(4,  customer.getId());			
			int rowUpdated = ps.executeUpdate();			
			if(rowUpdated > 0) {
				System.out.println("Customer updated");
			}
			else {
				System.out.println("Error updating customer with email = " + customer.getId());
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletecustomer(int id) {
		try {
			String sqlQuery = "DELETE FROM CUSTOMER WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Customer deleted");
			}
			else {
				System.out.println("Error deleting customer with Id = " + id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

    @Override
    public Customer getCustomerByEmail(String email) {
        ResultSet rs = null;
		try {
			String sqlQuery = "select * from customer where email = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setString(1,email);

			rs = ps.executeQuery();

			while(rs.next()) {
				int customerId = rs.getInt("id");
				String emailId = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");		
				return new Customer(customerId, emailId, password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
    }

}
