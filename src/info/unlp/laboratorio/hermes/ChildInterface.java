package info.unlp.laboratorio.hermes;

import java.util.List;

public interface ChildInterface {

	public List<Child> selectAll();
	public String getNombre(int id);
	public List<String> recuperarChilds();
	public int getId(String nombreApellido);
}
