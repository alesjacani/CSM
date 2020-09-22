package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
	
	
	private static final String url = "jdbc:postgresql://localhost:5433/course_management_system";
	private static final String user = "postgres";
	private static final String password = "makarona123";
	
	public static Connection getConnection() {
		try {
		
			return DriverManager.getConnection(url,user,password);
			
		
		} catch (SQLException e) {
			System.out.println("Connection error "+ e);
			return null;
			
		}
		
	}
}

