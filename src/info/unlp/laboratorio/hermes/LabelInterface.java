package info.unlp.laboratorio.hermes;

import java.util.List;

public interface LabelInterface {

	public List<String> recuperarLabels();
	public boolean addLabel(String texto);
	public boolean deleteLabel(String texto);
	public boolean editLabel(String texto, String texto2);
	public int getId(String descripcion);
	
}
