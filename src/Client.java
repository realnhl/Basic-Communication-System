import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client
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

      System.out.println("Please enter password to use this application : ");
      Scanner input = new Scanner(System.in);
      String pass = input.next();

    while(!pass.equals(password)){
      System.out.println("Wrong password. Please try again.");
      input = new Scanner(System.in);
      pass = input.next();
    }

      System.out.println("Please enter IP of server to use this application : ");
      Scanner ip_input = new Scanner(System.in);
      InetAddress ip = InetAddress.getByName(ip_input.next());

    // initialize the connection to the server. 192.168.0.118 is Server's IP Address
     Socket sock = new Socket(ip.getHostAddress(), 3000);
     
     //read input from the keyboard
     BufferedReader readFromKeyboard = new BufferedReader(new InputStreamReader(System.in));
    
     //send the input to the server
     OutputStream outputStream = sock.getOutputStream(); 
     PrintWriter printWriter = new PrintWriter(outputStream, true);

     //receive input from the server
     InputStream inputStream = sock.getInputStream();
     BufferedReader readData = new BufferedReader(new InputStreamReader(inputStream));

     System.out.println("\n\nLet's Start Chatting!!");
     System.out.println("Type [exit] if you wish to terminate the connection");
    
     // variables to hold the value for the message.
     String receiveMessage =""; 
     String sendMessage = "";
     String ack = "";
     while(!sendMessage.equalsIgnoreCase("exit"))
     {
        System.out.print("You : ");
        //read the message from the keyboard entered by the client's user
        sendMessage = readFromKeyboard.readLine();  
        //send to the server
        printWriter.println(sendMessage);
        //flush the data inside pwrite object.
        printWriter.flush();
        if(sendMessage.equalsIgnoreCase("exit")){
          return;
        }

         //receive ACK from the server.
         if((ack = readData.readLine()) != null)
         {
             // print the data
             System.out.println("[ACK from Server] :\t " + ack);
             if(ack.equalsIgnoreCase("exit")){
                 return;
             }
         }

        //receive data from the server.
        if((receiveMessage = readData.readLine()) != null)
        {
            // print the data
            System.out.println("Server : " + receiveMessage);
            if(receiveMessage.equalsIgnoreCase("exit")){
              return;
            }
        }
      }               
    }                    
}                        