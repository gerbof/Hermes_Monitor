package info.unlp.laboratorio.hermes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContextDAO implements ContextInterface{

	private Connection c = SQLiteJDBC.getC();
	
	@SuppressWarnings("finally")

	public List<Context> selectAll() {
		List<Context> list = new ArrayList<Context>();
		try{
			Statement stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM Context;" );
		    while (rs.next()){
		        list.add(new Context(rs.getInt("id"), rs.getString("descripcion"))); 
	    	}
	    	rs.close();
	    	stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}
	
	@SuppressWarnings("finally")

	public List<String> selectAllStrings() {
		List<String> list = new ArrayList<String>();
		try{
			Statement stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM Context;" );
		    while (rs.next()){
		        list.add(rs.getString("descripcion")); 
	    	}
	    	rs.close();
	    	stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}


	public String getDescripcion(int id) {
		String result = "";
		try{
			String query = "SELECT descripcion FROM Context where id = ?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setInt(1, id);
			ResultSet rs = q.executeQuery();
	    	result = rs.getString("descripcion");
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return result;
		}
	}


	public int getId(String descripcion) {
		int result=0;
		try{
			String query = "SELECT id FROM Context where descripcion = ?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, descripcion);
			ResultSet rs = q.executeQuery();
			while(rs.next())
				result = rs.getInt("id");
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return result;
		}
	}	
}
