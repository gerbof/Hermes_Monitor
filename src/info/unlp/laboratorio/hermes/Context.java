package info.unlp.laboratorio.hermes;

public class Context {

	private int id;
	private String descripcion;
	
	public Context(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Context(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return descripcion;
	}
	
}
