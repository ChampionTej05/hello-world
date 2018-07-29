/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnpratical2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author champion
 */

class ServerThreadHandler extends Thread{
    Socket s;

    public ServerThreadHandler(Socket socket) {
        this.s = socket;
    }

    @Override
    public void run() {
      if(s!=null){
            DataInputStream dis=null;
            Scanner sc=new Scanner(System.in);
          try {
              System.out.println("Connection is Successful");
              dis = new DataInputStream(s.getInputStream());
              DataOutputStream dos=new DataOutputStream(s.getOutputStream());
              {
                  String msg=dis.readUTF();
                  if(msg.equalsIgnoreCase("Exit")){
                      System.out.println("Disconnecting with the Server");
                      s.close();
                     
                  }
                  System.out.println("Message received From Client: "+msg);
               
                  String reply="Thanks -> ";
                  dos.writeUTF(reply);
                  
                  
                  
              } dos.close();
              dis.close();
              s.close();
          } catch (IOException ex) {
              Logger.getLogger(ServerThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
              try {
                  dis.close();
              } catch (IOException ex) {
                  Logger.getLogger(ServerThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
                     
            }
    }
    
    
    
    
    
    
}


public class ServerThreadMain {
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(5555);
            serverSocket.setSoTimeout(10000);
            while(true){
                try{
                Socket t=serverSocket.accept();
                new Thread(new ServerThreadHandler(t)).start();
                }
                catch(SocketTimeoutException se){
                    break;
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
