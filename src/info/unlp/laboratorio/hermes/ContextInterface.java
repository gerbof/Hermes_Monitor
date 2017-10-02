package info.unlp.laboratorio.hermes;

import java.util.List;

public interface ContextInterface {

	public List<Context> selectAll();
	public List<String> selectAllStrings();
	public String getDescripcion(int id);
	public int getId(String descripcion);
}
