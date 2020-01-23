package es.corenetworks.starwars;
import java.util.ArrayList;

import org.json.JSONObject;

public class Personaje {
	private String name;
	private String height;
	private String mass;
	private String hair_color;
	private String skin_color;
	private String eye_color;
	private String birth_year;
	private String gender;
	private String homeworld;
	private ArrayList<String> films;
	private ArrayList<String> species = new ArrayList<String>();
	private ArrayList<String> vehicles = new ArrayList<String>();
	private ArrayList<String> starships = new ArrayList<String>();
	private String created;
	private String edited;
	private String url;

	public Personaje(JSONObject json) {
		int size;
		this.name = (json.has("name")) ? json.getString("name") : "Desconocido";
		this.height = (json.has("height")) ? json.getString("height") : "n/a";
		this.mass = (json.has("mass")) ? json.getString("mass") : "n/a";
		this.hair_color = (json.has("hair_color")) ? json.getString("hair_color") : "n/a";
		this.skin_color = (json.has("skin_color")) ? json.getString("skin_color") : "n/a";
		this.eye_color = (json.has("eye_color")) ? json.getString("eye_color") : "n/a";
		this.birth_year = (json.has("birth_year")) ? json.getString("birth_year") : "n/a";
		this.gender = (json.has("gender")) ? json.getString("gender") : "n/a";
		this.homeworld = (json.has("homeworld")) ? json.getString("homeworld") : "n/a";
		this.created = (json.has("created")) ? json.getString("created") : "n/a";
		this.edited = (json.has("edited")) ? json.getString("edited") : "n/a";
		this.url = (json.has("url")) ? json.getString("url") : "n/a";
		// La inicialización se suele hacer todas en el constructor
		this.films = new ArrayList<String>();
		if (json.has("films")) {
			size = json.getJSONArray("films").length();
			for (int i = 0; i < size; i++) {
				this.films.add(json.getJSONArray("films").getString(i));
			}
		}
		// Así en todos
		if (json.has("species")) {
			size = json.getJSONArray("species").length();
			for (int i = 0; i < size; i++) {
				this.species.add(json.getJSONArray("species").getString(i));
			}
		}
		if (json.has("vehicles")) {
			size = json.getJSONArray("vehicles").length();
			for (int i = 0; i < size; i++) {
				this.vehicles.add(json.getJSONArray("vehicles").getString(i));
			}
		}
		if (json.has("starships")) {
			size = json.getJSONArray("starships").length();
			for (int i = 0; i < size; i++) {
				this.starships.add(json.getJSONArray("starships").getString(i));
			}
		}
	}

	public Personaje(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getHair_color() {
		return hair_color;
	}

	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}

	public String getSkin_color() {
		return skin_color;
	}

	public void setSkin_color(String skin_color) {
		this.skin_color = skin_color;
	}

	public String getEye_color() {
		return eye_color;
	}

	public void setEye_color(String eye_color) {
		this.eye_color = eye_color;
	}

	public String getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeworld() {
		return homeworld;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	public ArrayList<String> getFilms() {
		return films;
	}

	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}

	public ArrayList<String> getSpecies() {
		return species;
	}

	public void setSpecies(ArrayList<String> species) {
		this.species = species;
	}

	public ArrayList<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<String> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<String> getStarships() {
		return starships;
	}

	public void setStarships(ArrayList<String> starships) {
		this.starships = starships;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
