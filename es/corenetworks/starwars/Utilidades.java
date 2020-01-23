package es.corenetworks.starwars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utilidades {

	public static String leerArchivo(String rutaArchivo) {
		try {
			return new String(Files.readAllBytes(Paths.get(rutaArchivo)));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean escribirArchivo(String rutaArchivo, String contenidoArchivo) {
		try {
			  // abre el archivo rutaArchivo  para leer y escribir (rw)
			  Path path = Paths.get(rutaArchivo);
			  // escribe en el archivo
			  Files.write(path, contenidoArchivo.getBytes());
			  return true;
			// la excepción IOException es capturada
			} catch (IOException e) {
			  e.printStackTrace();
			}
			return false;
	}
	
	public static String httpGet(String urlGet) {
		try {

			  // crea el objeto HttpURLConnection a partir de un objeto de clase URL
			  URL url = new URL(urlGet);
			  HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			  // se establece el método GET de HTTP
			  connection.setRequestMethod("GET");

			  // se establece el valor del campo Accept de la cabecera HTTP
			  connection.setRequestProperty("Accept", "application/json");

			  // se establece la comunicación
			  connection.connect();
			  
			  // lee los datos del recurso
			  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			  StringBuilder content = new StringBuilder();
			  String line = in.readLine();
			  while (line != null) {
			    content.append(line);
			    line = in.readLine();
			  }

			  // cerrar la conexión
			  in.close();
			  
			  // obtiene el código de estado HTTP
//			  System.out.println("Código HTTP " + connection.getResponseCode());
			  return content.toString();

			} catch (ProtocolException e) {
			  e.printStackTrace();
			} catch (MalformedURLException e) {
			  e.printStackTrace();
			} catch (FileNotFoundException e) {
//				  e.printStackTrace();
//				System.out.println("Este fichero no existe!");
				return null;
			} catch (IOException e) {
			  e.printStackTrace();
			}
			return null;
	}
	public static void crearDirectorio(String directorio) {
		File directory = new File(directorio);

		directory.mkdirs();
		
	}
}
