package edu.neu.csye6200.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	//private constructor
	private DatabaseConnection() {
		
	}
	
    public static final Connection getDbInstance() {
    	String dbFileName = "properties/ConnectionDetails.txt";
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3307/ood_finalproject1" ;//= "jdbc:mysql://127.0.0.1:3306/ood_finalproject";
        String username = "root";// = "root";
        String password = "root";// = "root";
       
        Connection connection = null;
        try {
             FileReader fileReader = new FileReader(dbFileName);
             BufferedReader br = new BufferedReader(fileReader);
             String fileLine = br.readLine();
             while(fileLine != null) {
            	 System.out.println("File Line = " + fileLine);
            	 String[] credentials = fileLine.split("=");
            	 if(credentials[0].equals("jdbcUrl")) {
            		 jdbcUrl = credentials[1];
            	 }else if(credentials[0].equals("username")) {
            		 username = credentials[1];
            	 }else if(credentials[0].equals("password")) {
            		 password = credentials[1];
            	 }
            	 
            	 fileLine = br.readLine();
             }
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            System.out.println("jdbcurl = " + jdbcUrl + "\nusername = " + username + "\nPass = " + password);
            
            if(connection == null) {
            	connection = DriverManager.getConnection(jdbcUrl, username, password);
            	System.out.println("Connection found");
            }else {
            	System.out.println("Connection not found");
            }
            // Perform database operations...
            
            // Close the connection
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return connection;
    }
}
