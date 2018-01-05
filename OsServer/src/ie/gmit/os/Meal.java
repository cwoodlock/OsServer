package ie.gmit.os;

/*
 * Colm Woodlock
 * G00341460
 * https://github.com/cwoodlock/OsServer
 * This is a project for 3rd year module Operating Systems in GMIT
 */

//This class will have all the meal data

public class Meal {

	//variables
	private String type;
	private String description;
	
	//Constructor
	
	public Meal() {
		
	}
	
	public Meal(String type, String description) {
		this.type = type;
		this.description = description;
	}

	//Getters and setters
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
