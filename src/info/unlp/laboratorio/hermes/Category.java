package info.unlp.laboratorio.hermes;

public class Category {
	
	private int id;
	private String descripcion;
	private int idContext;
	
	
	public Category(int id, String descripcion, int idContext) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idContext = idContext;
	}

	public Category(String descripcion, int idContext) {
		super();
		this.descripcion = descripcion;
		this.idContext = idContext;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdContext() {
		return idContext;
	}

	public void setIdContext(int idContext) {
		this.idContext = idContext;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return descripcion;
	}
}
