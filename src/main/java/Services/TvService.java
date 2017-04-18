/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Model.Tv;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mark Kirby
 */
public class TvService implements Runnable{
    
    //server constructor
    public TvService(){
        
    }

    @Override
    public void run() {
  
        try {
            
            ServerSocket listener = new ServerSocket(9092);
            try {
                
                // Create a JmDNS instance
                JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
                
                // Register a service
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "Tv Service", 9092, "can't be empty?");
                jmdns.registerService(serviceInfo);
                System.out.println("Tv Service is registered");
                
                //print message
                while (true) {
                    Socket socket = listener.accept();
                    
                    try {
                        
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
                        out.println("Tv is on and Ready");
                       
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
        }   catch (IOException ex) {
            Logger.getLogger(TvService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

