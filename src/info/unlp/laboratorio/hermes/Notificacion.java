package info.unlp.laboratorio.hermes;

public class Notificacion {

	private int id;
	private String contenido;
	private int idContext;
	private int idCategory;
	private String fechaHoraEnvio;
	private String fechaHoraRecepcion;
	private int idChild;
	
	public Notificacion(int id, String contenido, int idContext, int idCategory, String fechaHoraEnvio,
			String fechaHoraRecepcion, int idChild) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.idContext = idContext;
		this.idCategory = idCategory;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.fechaHoraRecepcion = fechaHoraRecepcion;
		this.idChild = idChild;
	}

	public Notificacion(String contenido, int idContext, int idCategory, String fechaHoraEnvio,
			String fechaHoraRecepcion, int idChild) {
		super();
		this.contenido = contenido;
		this.idContext = idContext;
		this.idCategory = idCategory;
		this.fechaHoraEnvio = fechaHoraEnvio;
		this.fechaHoraRecepcion = fechaHoraRecepcion;
		this.idChild = idChild;
	}
	
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getIdContext() {
		return idContext;
	}
	public void setIdContext(int idContext) {
		this.idContext = idContext;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getFechaHoraEnvio() {
		return fechaHoraEnvio;
	}
	public void setFechaHoraEnvio(String fechaHoraEnvio) {
		this.fechaHoraEnvio = fechaHoraEnvio;
	}
	public String getFechaHoraRecepcion() {
		return fechaHoraRecepcion;
	}
	public void setFechaHoraRecepcion(String fechaHoraRecepcion) {
		this.fechaHoraRecepcion = fechaHoraRecepcion;
	}
	public int getIdChild() {
		return idChild;
	}
	public void setIdChild(int idChild) {
		this.idChild = idChild;
	}
	public int getId() {
		return id;
	}
	
	
}
