/**
 * 
 */
package com.example.ConnectionManagerJDBC.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


import com.example.ConnectionManagerJDBC.projectConstants.DataBaseConstants;

/**
 * @author Aritra
 * To create a Connection with the Data Base.
 *
 */
public class ConnectionManager {
	private static String JDBC_DRIVER   = DataBaseConstants.JDBC_DRIVER;
    private static String JDBC_URL      = DataBaseConstants.DB_URL;
    private static String JDBC_USER     = DataBaseConstants.USER_NAME;
    private static String JDBC_PASSWORD = DataBaseConstants.DB_PASSWORD;
    private static Driver driver = null;
    
    public static synchronized Connection getJDBCConnection() throws SQLException {		
    	if (driver == null) {
			try {
				Class<?> jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver( driver );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		System.out.println("Connecting to DB : " + JDBC_URL);
		return conn;
 	}

}
