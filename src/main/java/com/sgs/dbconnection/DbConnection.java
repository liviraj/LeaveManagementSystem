package com.sgs.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Database connection code
 */
public class DbConnection {
	static Connection connection=null;
	private static final String className="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/lms";
	private static final String root="root";
	// private static String password="Historaj@007";
	private static final String password="Raji@2024";

	public static Connection getConnection() throws ClassNotFoundException , SQLException{
		try{		
		Class.forName(className);
		connection=(Connection) DriverManager.getConnection(url,root,password);
		return connection;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return connection;	
	}

	}


