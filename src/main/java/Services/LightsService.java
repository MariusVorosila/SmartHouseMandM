/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/**
 *
 * @author Mariuss
 */
public class LightsService implements Runnable{
    
     //server constructor
    public LightsService(){
        
    }

    @Override
    public void run() {
  
        try {
            
            ServerSocket listener = new ServerSocket(9093);
            try {
                
                // Create a JmDNS instance
                JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
                
                // Register a service
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "Lights Service", 9093, "can't be empty?");
                jmdns.registerService(serviceInfo);
                System.out.println("Lights Service is registered");
                
                //print message
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Lights are On");
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
        }   catch (IOException ex) {
            Logger.getLogger(LightsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
