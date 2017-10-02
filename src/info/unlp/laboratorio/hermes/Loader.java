package info.unlp.laboratorio.hermes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Loader {

	FactoryDAO factory = new FactoryDAO();
	
	public void load(){
		JSONParser parser = new JSONParser();
		Object obj=null;
		try {

			
			try {
				obj = parser.parse(new FileReader(new File(this.getClass().getClassLoader().getResource("resources//load.json").toURI())));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray tags = (JSONArray) jsonObject.get("Notificaciones");
			Iterator<Object> iterator = tags.iterator();
			while (iterator.hasNext()) {
				JSONObject elem = (JSONObject) iterator.next();
				String contenido = elem.get("contenido").toString();
				int idContext = Integer.parseInt(elem.get("idContext").toString());
				int idCategory = Integer.parseInt( elem.get("idCategory").toString());
				String fechaEnvio = elem.get("fechaHoraEnvio").toString();
				int idChild = Integer.parseInt(elem.get("idChild").toString());
				factory.getNotifyDAO().addNotify(new Notificacion(contenido, idContext, idCategory, fechaEnvio, null, idChild));
			}

		} catch (FileNotFoundException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		} catch (IOException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		} catch (ParseException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
}
