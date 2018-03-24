/*
This class is responsible for the creation of Gui of java server
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class JavaServerGui extends JFrame{
    
    public static JTextArea clientMessage = new JTextArea();
    
    
    public JavaServerGui()
    {
        initUI();
    
    }
    
    
    private void initUI()
    {
        setBounds(0,0,600,400);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        
        panel.setLayout(new BorderLayout());
    
        panel.setForeground(Color.red);
        add(panel);
        
        
        JButton startServer = new JButton("Start Server");
        
        JButton exit = new JButton("Stop and exit");
        
        
         JButton changePort = new JButton("Change Port");
        
        
         clientMessage.append("Message From Client\n");
     
        clientMessage.setFont(clientMessage.getFont().deriveFont(13f));
        
        clientMessage.setForeground(Color.white);
        
        clientMessage.setBackground(Color.GRAY);
        
        
        panel.add(startServer,BorderLayout.WEST);
        
        panel.add(exit,BorderLayout.EAST);
        
        panel.add(changePort,BorderLayout.NORTH);
        
        panel.add(clientMessage,BorderLayout.CENTER);
        
        setVisible(true);
     
        
        
        startServer.addActionListener((ActionEvent e) -> {
         
            Threads t = new Threads("start_server");
      
            t.start();
   
        });
        
        
         exit.addActionListener((ActionEvent e) -> {
             
               Threads t = new Threads("stop_server");
            
               t.start();
            
             
         });
         
           changePort.addActionListener((ActionEvent e) -> {
           
            Threads t = new Threads("change_port");
            
            t.start();
            
           
   
        });
         
        
    }
    
    public void printTextField(String text)  
    {
  
    clientMessage.append(text);
    }
    
    
   
     public static void main(String[] args) {
     
        JavaServerGui j = new JavaServerGui();
        
    }
  
    
}
