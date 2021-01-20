import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Server
{
  public static void main(String[] args) throws Exception
  {

      //Welcome Message
      System.out.println("-------------------------------------------------------------------------------\n");
      System.out.println("Welcome and Hi!");
      System.out.println("This is a Chat Application!");
      System.out.println("You can use this application to chat with your friend in same network!");
      System.out.println("Developed by : Aima, Lau, Woo and Faizal\n");
      System.out.println("-------------------------------------------------------------------------------\n\n");

      String password = "TMN3073";
      String pass = "";
      
      System.out.println("Please enter password to use this application : ");
      Scanner input = new Scanner(System.in);
      pass = input.next();

      while(!pass.equals(password)){
        System.out.println("Wrong password. Please try again.");
        input = new Scanner(System.in);
        pass = input.next();
      }

      //Initialize a connection for the server. Client will connected to this server.
      ServerSocket serverSock = new ServerSocket(3000);
      System.out.println("\n\nServer has been started. Waiting for incoming messages!");
      System.out.println("Type [exit] if you wish to terminate the connection");

      Socket sock = serverSock.accept( );

      //read input from the keyboard
      BufferedReader readFromKeyboard = new BufferedReader(new InputStreamReader(System.in));

      //send the input to the client
      OutputStream outputStream = sock.getOutputStream(); 
      PrintWriter printWriter = new PrintWriter(outputStream, true);

      //receive input from the client
      InputStream inputStream = sock.getInputStream();
      BufferedReader readData = new BufferedReader(new InputStreamReader(inputStream));

      // variables to hold the value for the message.
      String receiveMessage ="";
      String sendMessage ="";
      String ack= "** Message is successfully received by server! **";
      while(!sendMessage.equalsIgnoreCase("exit"))
      {
        //receive data from the client.
        if((receiveMessage = readData.readLine()) != null)  
        {
          // print the data
          System.out.println("Client : " + receiveMessage);
            //send ACK to the server
            printWriter.println(ack);
            //flush the data inside pwrite object.
            printWriter.flush();
          if(receiveMessage.equalsIgnoreCase("exit")){
            System.out.println("Client has left the server.");
            return;
          }      
        }

        System.out.print("You : ");
        //read the message from the keyboard entered by the client's user
        sendMessage = readFromKeyboard.readLine();
        //send to the server 
        printWriter.println(sendMessage);
        //flush the data inside pwrite object.             
        printWriter.flush();
      }               
    }                    
}