package info.unlp.laboratorio.hermes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChildDAO implements ChildInterface{

	private Connection c = SQLiteJDBC.getC();
	
	@SuppressWarnings("finally")

	public List<Child> selectAll() {
		List<Child> list = new ArrayList<Child>();
		try{
			Statement stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM Child;" );
		    while (rs.next()){
		        list.add(new Child(rs.getString("nombre"), rs.getString("apellido"))); 
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
	

	public List<String> recuperarChilds() {
		List<String> list = new ArrayList<String>();
		try{
			String query = "SELECT DISTINCT nombre, apellido FROM Child;";
			PreparedStatement q = c.prepareStatement(query);
			ResultSet rs = q.executeQuery();
	    	while (rs.next()){
		        list.add(rs.getString("nombre")+", "+rs.getString("apellido")); 
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}


	public String getNombre(int id) {
		String result = "";
		try{
			String query = "SELECT nombre, apellido FROM Child where id = ?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setInt(1, id);
			ResultSet rs = q.executeQuery();
	    	result = rs.getString("nombre")+", "+rs.getString("apellido");
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return result;
		}
	}	
	

	public int getId(String nombreApellido) {
		int result=0;
		String[] split = nombreApellido.split(",");
		String nombre = split[0].trim();
		String apellido= split [1].trim();
		try{
			String query = "SELECT id FROM Child where nombre = ? AND apellido = ?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, nombre);
			q.setString(2, apellido);
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
