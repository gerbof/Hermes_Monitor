package info.unlp.laboratorio.hermes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificacionDAO implements NotificacionInterface {

	private Connection c = SQLiteJDBC.getC();
	private FactoryDAO factory = new FactoryDAO();
	
	
	public List<String> recuperarEtiquetas(int id) {
		List<String> list = new ArrayList<String>();
		try{
			String query = "SELECT descripcion, idNotify FROM NotifyLabel INNER JOIN Label ON (NotifyLabel.idLabel = Label.id) WHERE idNotify =?;";
			PreparedStatement q = c.prepareStatement(query);
			q.setInt(1, id);
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
	
	
	public List<String> recuperarContenidos() {
		List<String> list = new ArrayList<String>();
		try{
			String query = "SELECT DISTINCT contenido FROM Notify;";
			PreparedStatement q = c.prepareStatement(query);
			ResultSet rs = q.executeQuery();
	    	while (rs.next()){
		        list.add(rs.getString("contenido")); 
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}
	
	
	public void addNotify(Notificacion not) {
		try{
			String query = "INSERT INTO Notify (contenido, idContext, idCategory, fechaHoraEnvio, fechaHoraRecepcion, idChild) VALUES(?,?,?,?,?,?)";
			PreparedStatement q = c.prepareStatement(query);
			q.setString(1, not.getContenido());
			q.setInt(2, not.getIdContext());
			q.setInt(3, not.getIdCategory());
			q.setString(4, not.getFechaHoraEnvio());
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date today = Calendar.getInstance().getTime();        
		
			String reportDate = df.format(today);
			q.setString(5, reportDate);
			q.setInt(6, not.getIdChild());
			q.executeUpdate();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		
	}

	
	public List<Notificacion> getAllNotifies() {
		List<Notificacion> list = new ArrayList<Notificacion>();
		try{
			String query = "SELECT * FROM Notify;";
			PreparedStatement q = c.prepareStatement(query);
			ResultSet rs = q.executeQuery();
	    	while (rs.next()){
		        list.add(new Notificacion(rs.getInt("id"),rs.getString("contenido"), rs.getInt("idContext"), rs.getInt("idCategory"), rs.getString("fechaHoraEnvio"), rs.getString("fechaHoraRecepcion"), rs.getInt("idChild"))); 
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}

	
	public List<Notificacion> getFilterNotifies(String contenido, String contexto, String categoria, String fecha1,
			String fecha2, String child, String etiqueta) {
		List<Notificacion> list = new ArrayList<Notificacion>();
		int idContexto = factory.getContextDAO().getId(contexto);
		int idCategoria = factory.getCategoryDAO().getId(categoria, idContexto);
		int idChild = factory.getChildDAO().getId(child);
		int idLabel = factory.getLabelDAO().getId(etiqueta);
		try{
			String query = "SELECT * FROM Notify LEFT JOIN NotifyLabel ON (Notify.id = NotifyLabel.idNotify) "
					+ "WHERE /*fechaHoraEnvio >= ? AND fechaHoraRecepcion <=? AND*/ idCategory = ? AND idContext = ? AND idChild = ? "
					+ "AND contenido = ?";
			if(idLabel != 0)
				query += "AND idLabel = ?";
			PreparedStatement q = c.prepareStatement(query);
			//q.setString(1, fecha1);
		//	q.setString(2, fecha2);
			q.setInt(1, idCategoria);
			q.setInt(2, idContexto);
			q.setInt(3, idChild);
			q.setString(4, contenido);
			if(idLabel != 0)
				q.setInt(5, idLabel);
			ResultSet rs = q.executeQuery();
	    	while (rs.next()){
		        list.add(new Notificacion(rs.getInt("id"),rs.getString("contenido"), rs.getInt("idContext"), rs.getInt("idCategory"), rs.getString("fechaHoraEnvio"), rs.getString("fechaHoraRecepcion"), rs.getInt("idChild"))); 
	    	}
	    	rs.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		finally{
			return list;
		}
	}

	
	public void truncate() {
		try{
			String query = "DELETE FROM Notify; ";
			PreparedStatement q = c.prepareStatement(query);
			q.executeUpdate();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		
	}

}
