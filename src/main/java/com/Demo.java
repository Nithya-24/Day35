package com;

import java.sql.DriverManager;
import java.util.Enumeration;

import com.mysql.cj.jdbc.Driver;

/**
 * 
 * @author DELL
 *
 */
public class Demo {
	
	/**
	 * create a database Payroll Service
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "Nithya1234@";
		java.sql.Connection Connection;
		
		/**
		 * try and catch block to handle the excep
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded Driver");
		}catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath", e);
		}
		/**
		 * connect to database
		 */
		
		listDrivers();
		try {
			System.out.println("connecting to the database:" + jdbcUrl);
			Connection =  DriverManager.getConnection(jdbcUrl,userName,password);
			System.out.println("connection is successful! " +Connection);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
		while(driverList.hasMoreElements()) {
			Driver driverClass =(Driver) driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
		
	}


}
