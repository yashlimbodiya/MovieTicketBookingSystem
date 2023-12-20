package edu.neu.csye6200.dao;

import java.util.List;

import edu.neu.csye6200.model.Customer;

public interface CustomerDao {
	List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deletecustomer(int id);
    
    Customer getCustomerByEmail(String email);
}
