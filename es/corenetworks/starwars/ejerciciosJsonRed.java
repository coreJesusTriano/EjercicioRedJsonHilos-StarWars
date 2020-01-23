package es.corenetworks.starwars;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class ejerciciosJsonRed {

	public static void ejercicio1() {

		/*
		 * Ejercicio: Adaptar el ejercicio de la API de Star Wars (el enunciado se
		 * encuentra en el tema de Ficheros) para obtener los datos en formato JSON a
		 * través de la URL https://swapi.co/api/people/1, en lugar de leyendo un
		 * fichero. A continuación:
		 * 
		 * 1. Guardar los datos del personaje en formato JSON a un fichero.
		 * 
		 * servidor local= http://192.168.1.58:3000/people/1
		 */
		try {
			String contenido = Utilidades.httpGet("http://192.168.1.58:3000/people/1");
			if (contenido != null) {
				JSONObject personajeJson = new JSONObject(contenido);
				boolean ok = Utilidades.escribirArchivo("C:\\StarWars\\" + "personaje1.json",
						personajeJson.toString(3));
				if (!ok) {
					System.out.println("Algo salió mal!");
				} else {
					System.out.println("Todo fue bien");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio2() {

		/*
		 * Ejercicio: Adaptar el ejercicio de la API de Star Wars (el enunciado se
		 * encuentra en el tema de Ficheros) para obtener los datos en formato JSON a
		 * través de la URL https://swapi.co/api/people/1, en lugar de leyendo un
		 * fichero. A continuación:
		 * 
		 * 1. Guardar los datos del personaje en formato JSON a un fichero.
		 * 
		 * 2. Adaptar todo el programa para que descargue todos los datos en formato
		 * JSON de todos los personajes, almacenando cada uno en un archivo.
		 * 
		 * servidor local= http://192.168.1.58:3000/people/1
		 */
		int contador = 0;
		int i = 1;
		while (contador < 3) {
			try {
				String contenido = Utilidades.httpGet("http://192.168.1.58:3000/people/" + i);
				if (contenido != null) {
					JSONObject personajeJson = new JSONObject(contenido);
					boolean ok = Utilidades.escribirArchivo(
							"C:\\StarWars\\" + i + " " + personajeJson.get("name") + ".json",
							personajeJson.toString(3));
					if (!ok) {
						System.out.println("Algo salió mal!");
					} else {
						System.out.println("Todo fue bien, con " + i + " " + personajeJson.get("name"));
					}
				} else {
					contador++;
				}
			} catch (Exception e) {
				System.out.println("Algo salió mal! \nSe generó la siguiente excepción:");
				e.printStackTrace();
			}
			i++;
		}
	}

	public static void ejercicio3() {

		/*
		 * Ejercicio: Adaptar el ejercicio de la API de Star Wars (el enunciado se
		 * encuentra en el tema de Ficheros) para obtener los datos en formato JSON a
		 * través de la URL https://swapi.co/api/people/1, en lugar de leyendo un
		 * fichero. A continuación:
		 * 
		 * 1. Guardar los datos del personaje en formato JSON a un fichero.
		 * 
		 * 2. Adaptar todo el programa para que descargue todos los datos en formato
		 * JSON de todos los personajes, almacenando cada uno en un archivo.
		 * 
		 * 3. Adaptar todo el programa para que utilice un hilo de ejecución por cada
		 * personaje.
		 * 
		 * servidor local= http://192.168.1.58:3000/people/1
		 */
		long startTime = System.currentTimeMillis();

		ArrayList<Thread> hilos = new ArrayList<Thread>();

		for (int i = 1; i < 83; i++) {
			HiloPeticionHttp hilopersonaje = new HiloPeticionHttp(i);
			Thread hilo = new Thread(hilopersonaje);
			hilos.add(hilo);
		}
		for (Thread tarea : hilos) {
			tarea.start();
		}
		try {
			for (Thread tarea : hilos) {
				tarea.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis() - startTime;

		System.out.printf("El tiempo que ha tardado es de %d milisegundos", endTime).println();
	}

	public static void ejercicio4() {

		/*  Versión 2
		 * Ejercicio: Adaptar el ejercicio de la API de Star Wars (el enunciado se
		 * encuentra en el tema de Ficheros) para obtener los datos en formato JSON a
		 * través de la URL https://swapi.co/api/people/1, en lugar de leyendo un
		 * fichero. A continuación:
		 * 
		 * 1. Guardar los datos del personaje en formato JSON a un fichero.
		 * 
		 * 2. Adaptar todo el programa para que descargue todos los datos en formato
		 * JSON de todos los personajes, almacenando cada uno en un archivo.
		 * 
		 * 3. Adaptar todo el programa para que utilice un hilo de ejecución por cada
		 * personaje.
		 * 
		 * 4. Guardar en archivos el resto de datos proporcionados por la API de Star
		 * Wars siguiendo el procediento anterior para /people: /films, /starships,
		 * /vehicles, /species y /planets
		 * 
		 * servidor local= http://192.168.1.58:3000/people/1
		 */
		//public static final String urlServer = "https://swapi.co/api/";

		
		long startTime = System.currentTimeMillis();
		HashMap<String, String> resources = new HashMap<String, String>();
		resources.put("people", "name");
		resources.put("films", "title");
		resources.put("planets", "name");
		resources.put("vehicles", "name");
		resources.put("species", "name");
		resources.put("starships", "name");
//		String tipos[] = {"people", "films", "starships", "vehicles", "species", "planets"};

		ArrayList<Thread> hilos = new ArrayList<Thread>();
		for(String resource : resources.keySet()) { // recorre los recursos
			Utilidades.crearDirectorio(Main.directorioRuta + resource);
			for(int i=0; i<=100; i++) {
				Hilo hilo = new Hilo(i, resource, resources.get(resource));
				Thread thread = new Thread(hilo);
				thread.start();
			}
		}
		
		
//		for (int j = 0; j < tipos.length; j++) {
//			for (int i = 0; i <= 100; i++) {
//				HiloPeticionTipoHttp hiloXhttp = new HiloPeticionTipoHttp(i, tipos[j]);
//				Thread hilo = new Thread(hiloXhttp);
//				hilos.add(hilo);
//			}
//		}
//		for (Thread tarea : hilos) {
//			tarea.start();
//		}
		try {
			for (Thread tarea : hilos) {
				tarea.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis() - startTime;

		System.out.printf("El tiempo que ha tardado es de %d milisegundos", endTime).println();
	}
	public static void ejercicio4_v1() {

		/*
		 * Ejercicio: Adaptar el ejercicio de la API de Star Wars (el enunciado se
		 * encuentra en el tema de Ficheros) para obtener los datos en formato JSON a
		 * través de la URL https://swapi.co/api/people/1, en lugar de leyendo un
		 * fichero. A continuación:
		 * 
		 * 1. Guardar los datos del personaje en formato JSON a un fichero.
		 * 
		 * 2. Adaptar todo el programa para que descargue todos los datos en formato
		 * JSON de todos los personajes, almacenando cada uno en un archivo.
		 * 
		 * 3. Adaptar todo el programa para que utilice un hilo de ejecución por cada
		 * personaje.
		 * 
		 * 4. Guardar en archivos el resto de datos proporcionados por la API de Star
		 * Wars siguiendo el procediento anterior para /people: /films, /starships,
		 * /vehicles, /species y /planets
		 * 
		 * servidor local= http://192.168.1.58:3000/people/1
		 */
		long startTime = System.currentTimeMillis();
		String tipos[] = {"people", "films", "starships", "vehicles", "species", "planets"};

		ArrayList<Thread> hilos = new ArrayList<Thread>();
		for (int j = 0; j < tipos.length; j++) {
			for (int i = 0; i <= 100; i++) {
				HiloPeticionTipoHttp hiloXhttp = new HiloPeticionTipoHttp(i, tipos[j]);
				Thread hilo = new Thread(hiloXhttp);
				hilos.add(hilo);
			}
		}
		for (Thread tarea : hilos) {
			tarea.start();
		}
		try {
			for (Thread tarea : hilos) {
				tarea.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis() - startTime;

		System.out.printf("El tiempo que ha tardado es de %d milisegundos", endTime).println();
	}

}
