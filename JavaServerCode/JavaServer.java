/*
  The javaServer class will act as a server and will recieve message from the JavaServer
 */


import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;


public class JavaServer  {
    
    
    
    
    private static int portNumber = 4444;
   
    
    private String message;
    
    int number = 0;
    
    ServerSocket serverSocket;
    
    public JavaServer()
    {
        
       
       try
       {
        
           System.out.println("server Starting at port Number "+ portNumber);
           
       //    JOptionPane.showMessageDialog(rootPane,  "server Starting at port Number \t"+ portNumber);
           
    //       serverSocket = new ServerSocket(portNumber);
           
           ServerSocket serverSocket = new ServerSocket(portNumber);
           
          
          
           //client connecting
           
           System.out.println("Waiting for the client to connect");
           
        //    JOptionPane.showMessageDialog(rootPane,  "Waiting for the client to connect");
         
        
        try
         {
         while(true)
         {
             
         
            new Threads(serverSocket.accept(),number).start();
           
         }
         }
        finally
        {
          serverSocket.close();
        }
        
           
         //  JOptionPane.showMessageDialog(rootPane,  "Client Connected");
         
           
          //recieve message from the client
          
          
          
       
        
       
        
  //      JOptionPane.showMessageDialog(rootPane,  "Server has ended");

       }
       catch(IOException io)
       {
           System.out.println(io);
       }
    
             System.out.println("Server has ended");
             
             System.exit(0);
    }
    
    
    
    
    public JavaServer(int portNum)
    {
    
          portNumber = portNum;
          
          
       
       try
       {
        
           System.out.println("server Starting at port Number "+ portNumber);
           
       //    JOptionPane.showMessageDialog(rootPane,  "server Starting at port Number \t"+ portNumber);
           
    //       serverSocket = new ServerSocket(portNumber);
           
           ServerSocket serverSocket = new ServerSocket(portNumber);
           
          
          
           //client connecting
           
           System.out.println("Waiting for the client to connect");
           
        //    JOptionPane.showMessageDialog(rootPane,  "Waiting for the client to connect");
         
        
        try
         {
         while(true)
         {
             
         
            new Threads(serverSocket.accept(),number).start();
           
         }
         }
        finally
        {
          serverSocket.close();
        }
        
           
         //  JOptionPane.showMessageDialog(rootPane,  "Client Connected");
         
           
          //recieve message from the client
          
          
          
       
        
       
        
  //      JOptionPane.showMessageDialog(rootPane,  "Server has ended");

       }
       catch(IOException io)
       {
           System.out.println(io);
           
           JOptionPane.showMessageDialog(null,io);
       }
    
             System.out.println("Server has ended");
             
        
          
          
    }

    /**
     * @return the portNumber
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }



    

    
}
