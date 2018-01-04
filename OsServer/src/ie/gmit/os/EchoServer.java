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
  String message;
  int clientID = -1;
  boolean running = true;
  ObjectOutputStream out;
  ObjectInputStream in;
  
  int choice;
  Random rand = new Random();
  int messageInt;
  double messageDouble;
  ArrayList<Profile> list = new ArrayList<Profile>();

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
  
  public void UI() {
	  
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
	  
	  int PPS=rand.nextInt(10) + 1;
	  
	  sendMessage("Please enter your age: ");
	  messageInt = (int)in.readObject();
	  int age = messageInt;
	  
	  sendMessage("Please enter your weight: ");
	  messageDouble = (double)in.readObject();
	  double weight = messageDouble;
	  
	  sendMessage("Please enter your height: ");
	  messageDouble = (double)in.readObject();
	  double height = messageDouble;
	  
	  list.add(new Profile(name, address, PPS, age, weight, height));
	  
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
				sendMessage("Press 1 for string testing\n Press 2 for the calculator \nPress 3 to exit");
				message = (String)in.readObject();
				
				if(message.compareToIgnoreCase("1")==0){
					
				}
				
				else if(message.compareToIgnoreCase("2")==0){
					
					
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

