package es.corenetworks.starwars;

import org.json.JSONObject;

public class Hilo implements Runnable {
	
	private int id;
	private String resource;
	private String filename;
	
	Hilo(int id, String resource, String filename) {
		this.id = id;
		this.resource = resource;
		this.filename = filename;
	}

	@Override
	public void run() {

		// http://192.168.1.58:3000/people/
		String url = Main.urlServer + resource + "/" + this.id;
		String contenido = Utilidades.httpGet(url);
		System.out.println(url + " --> " + contenido);

		if (contenido == null) {
			return;
		}

		JSONObject json = new JSONObject(contenido);
		
		if (!json.has(filename)) {
			return;
		}

		String name = json.getString(filename).replace("\\", "-");
		
		Utilidades.escribirArchivo(Main.directorioRuta + resource + "\\" + id + ". " + name + ".json", json.toString(3));
	}

}