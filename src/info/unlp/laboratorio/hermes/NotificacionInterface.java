package info.unlp.laboratorio.hermes;

import java.util.List;

public interface NotificacionInterface {
	
	public List<String> recuperarEtiquetas(int id);
	public List<String> recuperarContenidos();
	public void addNotify(Notificacion not);
	public List<Notificacion> getAllNotifies();
	public List<Notificacion> getFilterNotifies(String contenido, String contexto, String categoria, String fecha1, String fecha2, String child, String etiqueta);
	public void truncate();
}
