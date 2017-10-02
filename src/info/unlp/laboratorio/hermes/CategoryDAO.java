package info.unlp.laboratorio.hermes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CategoryInterface {

	private Connection c = SQLiteJDBC.getC();
	
	@SuppressWarnings("finally")
	public List<Category> selectAll() {
		List<Category> list = new ArrayList<Category>();
		try{
			Statement stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM Category;" );
		    while (rs.next()){
		        list.add(new Category(rs.getInt("id"), rs.getString("descripcion"), rs.getInt("idContext"))); 
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
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM Category;" );
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


	@SuppressWarnings("finally")
	public String getDescripcion(int id) {
		String result = "";
		try{
			String query = "SELECT descripcion FROM Category where id = ?;";
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


	@SuppressWarnings("finally")
	public List<String> selectAllStrings(String contexto) {
		List<String> list = new ArrayList<String>();
		try{
			String query = "SELECT Category.descripcion FROM Category INNER JOIN Context ON (Category.idContext = Context.id) where Context.descripcion = ? ORDER BY Category.id DESC;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, contexto);
			ResultSet rs = q.executeQuery();
		    while (rs.next()){
		        list.add(rs.getString("descripcion")); 
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}	
	

	@SuppressWarnings("finally")
	public int getId(String descripcion, int idContext) {
		int result=0;
		try{
			String query = "SELECT id FROM Category where descripcion = ? and idContext = ?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, descripcion);
			q.setInt(2, idContext);
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
