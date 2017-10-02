package info.unlp.laboratorio.hermes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LabelDAO implements LabelInterface {

	private Connection c = SQLiteJDBC.getC();
	

	public List<String> recuperarLabels() {
		List<String> list = new ArrayList<String>();
		try{
			String query = "SELECT DISTINCT descripcion FROM Label;";
			PreparedStatement q = c.prepareStatement(query);
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


	public boolean addLabel(String texto) {
		boolean result = false;
		try{
			String query = "SELECT DISTINCT descripcion FROM Label where descripcion =?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, texto);
			ResultSet rs = q.executeQuery();
	    	while (rs.next()){
		        return false;
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		
		try{
			String query = "INSERT INTO Label (descripcion) VALUES(?)";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, texto);
			q.executeUpdate();
			result = true;
			MonitorApp.repintarComboBoxes();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return result;
		}
		
	}
	
	

	public boolean editLabel(String textoOld, String textoNew) {
		boolean result = false;
		try{
			String query = "SELECT DISTINCT descripcion FROM Label where descripcion =?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, textoOld);
			ResultSet rs = q.executeQuery();
			while (!rs.next()){
		        return false;
	    	}			
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		
		try{
			String query = "UPDATE Label set descripcion =? WHERE descripcion = ?";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, textoNew);
			q.setString(2, textoOld);
			q.executeUpdate();
			result = true;
			MonitorApp.repintarComboBoxes();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return result;
		}
		
	}
	

	public boolean deleteLabel(String texto) {
		boolean result = false;
		try{
			String query = "SELECT DISTINCT descripcion FROM Label where descripcion =?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, texto);
			ResultSet rs = q.executeQuery();
	    	while (!rs.next()){
		        return false;
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		
		try{
			String query = "DELETE FROM Label WHERE descripcion = ?";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, texto);
			q.executeUpdate();
			result = true;
			MonitorApp.repintarComboBoxes();
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
			String query = "SELECT id FROM Label where descripcion = ?;";
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
