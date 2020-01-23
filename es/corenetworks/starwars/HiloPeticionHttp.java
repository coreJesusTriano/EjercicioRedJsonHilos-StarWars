package es.corenetworks.starwars;

import org.json.JSONObject;

public class HiloPeticionHttp implements Runnable {
	private int numPersonaje;
	
	public HiloPeticionHttp(int numPersonaje) {
		this.numPersonaje = numPersonaje;
	}

	@Override
	public void run() {
		
		try {
			String contenido = Utilidades.httpGet("http://192.168.1.58:3000/people/" + this.numPersonaje);
			if (contenido != null) {
				JSONObject personajeJson = new JSONObject(contenido);
				boolean ok = Utilidades.escribirArchivo(
						"C:\\StarWars\\" + this.numPersonaje + " " + personajeJson.get("name") + ".json",
						personajeJson.toString(3));
				if (!ok) {
					System.out.println("Algo salió mal!");
				} else {
					System.out.println("Todo fue bien, con " + this.numPersonaje + " " + personajeJson.get("name"));
				}
			} 
		} catch (Exception e) {
			System.out.println("Algo salió mal! \nSe generó la siguiente excepción:");
			e.printStackTrace();
		}
	}
		
}
