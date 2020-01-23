package es.corenetworks.starwars;

import org.json.JSONObject;

public class HiloPeticionTipoHttp implements Runnable {
	private int num;
	private String type;

	public HiloPeticionTipoHttp(int numPersonaje, String type) {
		super();
		this.num = numPersonaje;
		this.type = type;
	}

	@Override
	public void run() {

		try {
			String contenido = Utilidades.httpGet("http://192.168.1.58:3000/" + this.type + "/" + this.num);
			if (contenido != null) {
				JSONObject objectJson = new JSONObject(contenido);
				boolean ok = Utilidades.escribirArchivo(
						"C:\\StarWars\\" + this.type + "\\" + this.num + ".json",
						objectJson.toString(3));
				if (!ok) {
					System.out.println("Algo salió mal!");
				} else {
					System.out.println("Todo fue bien, con " + this.type + " " + this.num);
				}
			}
		} catch (Exception e) {
			System.out.println("Algo salió mal! \nSe generó la siguiente excepción:");
			e.printStackTrace();
		}
	}
}
