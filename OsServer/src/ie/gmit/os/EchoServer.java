package ie.gmit.os;
/*
 * Colm Woodlock
 * G00341460
 * https://github.com/cwoodlock/OsServer
 * This is a project for 3rd year module Operating Systems in GMIT
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.ArrayList;

public class EchoServer {
  public static void main(String[] args) throws Exception {
	ServerSocket m_ServerSocket = new ServerSocket(2004,10);
    int id = 0;
    while (true) {
      Socket clientSocket = m_ServerSocket.accept();
      ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
      cliThread.start();
    }
  }
}

class ClientServiceThread extends Thread {
  //Variables
  Socket clientSocket;
  String message, message2, tmpstr;
  int clientID = -1;
  boolean running = true;
  ObjectOutputStream out;
  ObjectInputStream in;
  
  int choice, tempint, choice2;
  Random rand = new Random();
  int messageInt;
  double messageDouble, tempDur;
  ArrayList<Profile> list = new ArrayList<Profile>();
  ArrayList<Fitness> fitlist = new ArrayList<Fitness>();
  ArrayList<Meal> meallist = new ArrayList<Meal>();
  boolean flag = false;
  String tempType, tempDesc;
  String tempMode;

  ClientServiceThread(Socket s, int i) {
    clientSocket = s;
    clientID = i;
  }

  //This sends the message to the client
  void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
  
  //This controls the UI which is shown to the user when they first connect
  public void UI() throws ClassNotFoundException, IOException {
	  
	  if(choice == 1) {
		  addUser();
	  } else if(choice ==2) {
		  login();
	  } 
  }
  
  //This adds a user to the database
  public void addUser() throws ClassNotFoundException, IOException {
	  sendMessage("Please enter your name: ");
	  message = (String)in.readObject();
	  String name = message;
	  
	  sendMessage("Please enter your address: ");
	  message = (String)in.readObject();
	  String address = message;
	  
	  sendMessage("Please enter your user name: ");
	  message = (String)in.readObject();
	  String username = message;
	  
	  sendMessage("Please enter your password: ");
	  message = (String)in.readObject();
	  String password = message;
	  
	  int PPS=rand.nextInt(10) + 1;
	  for(Profile a:list){
			while(a.getPPS()==PPS)
			{
				PPS=rand.nextInt(10) + 1;
			}
			break;
		}
	  
	  //HERE I WAS GETTING AN ERROR TRYING TO READ IN INTS/DOUBLES SO I GAVE THEM VALUES TO TEST OTHER FEATURES
	  
	 //sendMessage("Please enter your age: ");
	 // messageInt = (int)in.readObject();
	  int age = 15;//messageInt;
	  
	  sendMessage("Please enter your weight: ");
	  //messageDouble = (double)in.readObject();
	  double weight = 22;//messageDouble;
	  
	  sendMessage("Please enter your height: ");
	 // messageDouble = (double)in.readObject();
	  double height = 125;//messageDouble;
	  
	  list.add(new Profile(name, address,username, password, PPS, age, weight, height));
	  
	  System.out.println("/n/n" + list);
  }
  
  //This should perform the login check
  public void login()throws ClassNotFoundException, IOException {
	  
	  sendMessage("Please enter your user name: ");
	  message = (String)in.readObject();
	  
	  sendMessage("Please enter your password: ");
	  message2 = (String)in.readObject();
	  
	  for(Profile a:list){
			if(a.getUsername().equals(message) && a.getPassword().equals(message2))
			{
				tmpstr=a.getName();
				tempint = a.getPPS();
				flag=true;
				break;
			}
			else if(a.getUsername()!=(message) && a.getPassword()!=(message2))
			{
				System.out.println("Plaes try again");
				break;
			}
		}
  }
  
  //When log in is successful this displays the nect menu and controls it
  public void UILoginComp() throws ClassNotFoundException, IOException {
	  
	  sendMessage("You have logged in successfully \n Press 1 to add a fitness record \n Press 2 to add a meal \n Press 3 to view the last 10 meal records \n Press 4 to view the last 10 fitness records \n Press 5 to delete a record \n");
	  message = (String)in.readObject();
	  choice2 = new Integer(message);
	  
	  if(choice2 == 1) {
		  addFitness();
	  }else if (choice2 == 2) {
		  addMeal();
	  }else if (choice2 == 3) {
		  displayMeal();
	  }else if (choice2 == 4) {
		  displayFitness();
	  }else if (choice2 == 5) {
		  delete();
	  }
	  
  }
  
  //This deletes the record a user chooses
  public void delete() {
	  
	
  }

  //This displays the last 10 fitness records
  public void displayFitness() {
	  int i = 0;
	  while (i < 10) {
		for (Fitness f : fitlist) {
			tempMode = f.getMode();
			tempDur = f.getDuration();
			sendMessage("Meal Type: " + tempType + "\n Meal Description: " + tempDesc);
			i++;		
		}
	 }
	
  }

  //This displays the last 10 meal records
  public void displayMeal() {
	  int i = 0;
	  while (i < 10) {
		for (Meal m : meallist) {
			tempType = m.getType();
			tempDesc = m.getDescription();
			sendMessage("Meal Type: " + tempType + "\n Meal Description: " + tempDesc);
			i++;		
		}
	 }
  }

  //This adds a meal object
  public void addMeal() throws ClassNotFoundException, IOException {
	  sendMessage("Please enter your meal type(Breakfast, Lunch etc.) : ");
	  message = (String)in.readObject();
	  String type = message;
	  
	  sendMessage("Please enter a description to your meal(max 100 chars): ");
	  message = (String)in.readObject();
	  String description = message;
	  
	  meallist.add(new Meal(type, description));
	  
	  System.out.println("/n/n" + list);
	
  }

  //This adds a fitness object
  public void addFitness() throws ClassNotFoundException, IOException {
	  sendMessage("Please enter your fitness mode(Walking, Running etc.): ");
	  message = (String)in.readObject();
	  String mode = message;
	  
	  sendMessage("Please enter your fitness duration in hours(eg. hour and a half = 1.5): ");
	  messageInt = (int)in.readObject();
	  int duration = messageInt;
	  
	  fitlist.add(new Fitness(mode, duration));
	  
	  System.out.println("/n/n" + list);
  }

public void run() {
    System.out.println("Accepted Client : ID - " + clientID + " : Address - "
        + clientSocket.getInetAddress().getHostName());
    try 
    {
    	out = new ObjectOutputStream(clientSocket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(clientSocket.getInputStream());
		System.out.println("Accepted Client : ID - " + clientID + " : Address - "
		        + clientSocket.getInetAddress().getHostName());
		
		
		do{
			try
			{
				//displays the menu when the user first connects
				sendMessage("Press 1 for new user\n Press 2 for returning user \n Press x to exit");
				message = (String)in.readObject();
				choice = new Integer(message);
				
				
				UI();	//Enter the ui menu
				if(flag == true) {	//If login() was successful run the ui when logged in
					do {
						UILoginComp();	//Run the method for UI login completed
					}while(flag == true);
				}
				
			}
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
			
    	}while(!message.equals("x"));
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

