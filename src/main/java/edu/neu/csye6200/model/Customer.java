package edu.neu.csye6200.model;

public class Customer {
	private int id;
	private String email;
	private String password;
	public Customer(int id, String email, String password, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	public Customer(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + "Email=" + email + " Name=" + name + "]";
	}
}
