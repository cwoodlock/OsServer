package ie.gmit.os;

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
  double messageDouble;
  ArrayList<Profile> list = new ArrayList<Profile>();
  boolean flag = false;

  ClientServiceThread(Socket s, int i) {
    clientSocket = s;
    clientID = i;
  }

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
  
  public void UI() throws ClassNotFoundException, IOException {
	  
	  if(choice == 1) {
		  addUser();
	  } else if(choice ==2) {
		  login();
	  } 
  }
  
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
	  
	  sendMessage("Please enter your age: ");
	  messageInt = (int)in.readObject();
	  int age = messageInt;
	  
	  sendMessage("Please enter your weight: ");
	  messageDouble = (double)in.readObject();
	  double weight = messageDouble;
	  
	  sendMessage("Please enter your height: ");
	  messageDouble = (double)in.readObject();
	  double height = messageDouble;
	  
	  list.add(new Profile(name, address,username, password, PPS, age, weight, height));
	  
	  System.out.println("/n/n" + list);
  }
  
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
  
  public void UILoginComp() throws ClassNotFoundException, IOException {
	  
	  sendMessage("You have logged in successfully \n Press 1 to add a fitness record \n Press 2 to add a meal \n Press 3 to view the last 10 meal records \n Press 4 to view the last 10 fitness records \n Press 5 to delete a record \n");
	  message = (String)in.readObject();
	  choice2 = new Integer(message);
	  
	  if(choice == 1) {
		  addFitness();
	  }else if (choice == 2) {
		  addMeal();
	  }else if (choice == 3) {
		  displayMeal();
	  }else if (choice == 4) {
		  displayFitness();
	  }else if (choice == 5) {
		  delete();
	  }
	  
  }
  
  public void delete() {
	
  }

  public void displayFitness() {
	
  }

  public void displayMeal() {
	
  }

  public void addMeal() {
	
  }

  public void addFitness() {
	
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
				sendMessage("Press 1 for new user\n Press 2 for returning user \n");
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
			
    	}while(!message.equals("3"));
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

