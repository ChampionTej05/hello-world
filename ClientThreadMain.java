/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnpratical2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author champion
 */

class ClientThreadHandler extends Thread{
    
    String ClientName;

    public ClientThreadHandler(String ClientName) {
        this.ClientName = ClientName;
    }

    @Override
    public void run() {
       Scanner sc=new Scanner(System.in);
     
         
         Socket s=null;
         DataInputStream dis=null;
         DataOutputStream dos=null;
                 
        try {
            s = new Socket("localhost",5555);
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            dis = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    {
         
           
         String msg="Hello by: ->"+ClientName;
           try {
               dos.writeUTF(msg);
           } catch (IOException ex) {
               Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
           }
         if(msg.equalsIgnoreCase("Exit")){
               try {
                   s.close();
               } catch (IOException ex) {
                   Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
               }
            
         }
         
           try {
               System.out.println("Reply from server : "+dis.readUTF());
           } catch (IOException ex) {
               Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
           }
     }
     
        try {
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    
    
}


public class ClientThreadMain {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new ClientThreadHandler("Client "+i)).start();
        }
    }
}
