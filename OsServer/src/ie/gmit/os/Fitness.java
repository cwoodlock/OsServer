package ie.gmit.os;

/*
 * Colm Woodlock
 * G00341460
 * https://github.com/cwoodlock/OsServer
 * This is a project for 3rd year module Operating Systems in GMIT
 */


//This class will have all the fitness data

public class Fitness {
	
	//variables
	private String mode;
	private double duration;
	
	//Constructors
	public Fitness() {
		
	}
	
	public Fitness(String mode, double duration) {
		this.mode = mode;
		this.duration = duration;
	}
	
	//Getters and setters
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

}
