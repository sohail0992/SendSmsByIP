
/*
The start server will be run in thread so that all other option still accessible to user like stoping server
*/
    

    
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import static java.rmi.server.LogStream.log;
import javax.swing.JOptionPane;

public class Threads implements Runnable {
    
    private String threadName;
    
    private Thread t;
    
    private int  no;

    private Socket socket;

    private String message;
    
    public Threads()
    {
    }
            
    public void start()          
    {
        if(t == null)
        {
          t = new Thread(this,threadName);
          t.start();
        }
     
       
    }

   public Threads(String name)
   {
     threadName = name;
   }
   
   
   
    public Threads(Socket socket, int clientNumber) {
        
         this.socket = socket;
         
         this.no = clientNumber;
         
         this.threadName = Integer.toString(clientNumber);

    }

   
   
    @Override
    public void run()
    {
        
        if(t.toString().contains("stop_server"))
           {
                         
                System.exit(0);
       
           }
           else if(t.toString().contains("change_port"))
           {
           
             
             String portNumber;
             
             portNumber = JOptionPane.showInputDialog( null, "Enter the value to change the defult port " ,JOptionPane.OK_CANCEL_OPTION);
            
             if (portNumber!=null) 
             {
            
             if(JOptionPane.OK_OPTION ==JOptionPane.OK_OPTION)
             {
             JavaServer jj = new JavaServer(Integer.parseInt(portNumber));
             
             JOptionPane.showMessageDialog(null, "Port Number change.Start Server again by clicking start server" );
          
             }
              else 
             {
                  JOptionPane.showMessageDialog(null, "No new Port assign,Listening on default Port" );
          
             }
             }
             else 
             {
                  JOptionPane.showMessageDialog(null, "No new Port assign,Listening on default Port" );
          
             }
          
           }
        
           else if(t.toString().contains("start_server"))
           {
              JavaServer jj = new JavaServer();
           
           
           
           }
        
         
        
           
        
          try
          {
               BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
                while(true)
                {
                    message = br.readLine();
                    
                    if(message == null||message.equals("."))
                    {
                        break;
                    }
                    
                  System.out.println(message);  
                  
                  
                  
                  JavaServerGui.clientMessage.append("\n"+message);
                    
                }
               
               
               
               
          }
          catch(IOException e)
          {
               log("Error handling client# " + no + ": " + e);
          }
           
            
          
           
       }
           
           
        
    public String getThreadName() {
        return threadName;
    }
    
 
    
}

