package info.unlp.laboratorio.hermes;

import java.util.List;

public interface CategoryInterface {

	public List<Category> selectAll();
	public List<String> selectAllStrings();
	public List<String> selectAllStrings(String contexto);
	public String getDescripcion(int id);
	public int getId(String descripcion, int idcontext);
	
}
