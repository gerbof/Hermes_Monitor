package info.unlp.laboratorio.hermes;

public class Child {

	private int id;
	private String nombre;
	private String apellido;
	
	public Child(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return apellido+", "+nombre;
	}
}
