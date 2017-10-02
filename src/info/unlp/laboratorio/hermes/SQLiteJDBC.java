package info.unlp.laboratorio.hermes;

import java.sql.*;

public class SQLiteJDBC{

	public static Connection c;
	
	public static void conectar(){
		c = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Hermes.db");
			System.out.println("Opened database successfully");
		}catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
	}
	
	public static Connection getC(){
		return c;
	}
	
	public static void desconectar(){
		try {
			c.close();
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
}