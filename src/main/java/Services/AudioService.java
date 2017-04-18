/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Model.Audio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mariuss
 */
public class AudioService implements Runnable{

    public AudioService() {
    }
    
    
    // make the service run on separete thread to avoid crush
    @Override
    public void run() {
  
        try {
            
            //initialize the socket of service
            ServerSocket listener = new ServerSocket(9090);
            try {
                
                // Create a JmDNS instance
                JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
                
                // Register a service
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "Audio Service", 9090, "can't be empty?");
                jmdns.registerService(serviceInfo);
                System.out.println("\n Audio Service is registered \n");
                
                //print message from service
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Audio is on and Ready to play");
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
        }   catch (IOException ex) {
            Logger.getLogger(AudioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
