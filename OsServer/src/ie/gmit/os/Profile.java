package ie.gmit.os;

//This class will have all the account data 

public class Profile {

	//Variables 
	private String name;
	private String address;
	private int PPS;
	private int age;
	private double weight;
	private double height;
	
	//Constructors
	public Profile(){
		
	}
	
	public Profile(String name, String address,int age, int PPS, double weight, double height){
		this.name =name;
		this.address = address;
		this.age = age;
		this.PPS =PPS;
		this.weight = weight;
		this.height = height;
	}
	
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPPS() {
		return PPS;
	}

	public void setPPS(int pPS) {
		PPS = pPS;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	//toString method
	@Override
	public String toString() {
		return "Profile [name=" + name + ", address=" + address + ", PPS=" + PPS + ", age=" + age + ", weight=" + weight
				+ ", height=" + height + "]";
	}
	
	

	
}
