package info.unlp.laboratorio.hermes;

public class FactoryDAO {

	public void conectar(){
		SQLiteJDBC.conectar();
	}
	
	public void desconectar(){
		SQLiteJDBC.desconectar();
	}
	
	public ChildDAO getChildDAO(){
		return new ChildDAO();
	}
	
	public ContextDAO getContextDAO(){
		return new ContextDAO();
	}
	
	public CategoryDAO getCategoryDAO(){
		return new CategoryDAO();
	}
	
	public NotificacionDAO getNotifyDAO(){
		return new NotificacionDAO();
	}
	
	public LabelDAO getLabelDAO(){
		return new LabelDAO();
	}
}
