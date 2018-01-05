package ie.gmit.os;

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
