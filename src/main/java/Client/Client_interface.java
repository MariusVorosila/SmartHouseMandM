/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Services.AudioService;
import Services.LightsService;
import Services.MoviesService;
import Services.TvService;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Marius & Mark Kirby
 */
public class Client_interface extends javax.swing.JFrame {

    /**
     * Creates new form Client_interface
     */
    public Client_interface() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        btnTv = new javax.swing.JButton();
        btnAudio = new javax.swing.JButton();
        btnSpeakers = new javax.swing.JButton();
        btnLights = new javax.swing.JButton();
        txtDisplay = new javax.swing.JScrollPane();
        InfoTxt = new javax.swing.JTextArea();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Smart Media");

        btnTv.setText("Tv");
        btnTv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTvActionPerformed(evt);
            }
        });

        btnAudio.setText("Audio System");
        btnAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAudioActionPerformed(evt);
            }
        });

        btnSpeakers.setText("Movies");
        btnSpeakers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpeakersActionPerformed(evt);
            }
        });

        btnLights.setText("Turn on lights");
        btnLights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLightsActionPerformed(evt);
            }
        });

        InfoTxt.setColumns(20);
        InfoTxt.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        InfoTxt.setRows(5);
        txtDisplay.setViewportView(InfoTxt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSpeakers, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLights, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(btnAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtDisplay, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTv, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSpeakers, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnLights, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(txtDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class ServiceListner implements ServiceListener {

        //get info of services
        @Override
        public void serviceAdded(ServiceEvent event) {
            ServiceInfo info = event.getInfo();
            int port = info.getPort();
            String serverAddress = info.getHostAddresses()[0];
            System.out.println("port = " + port + " host = " + serverAddress);
            
            //print the message from server
            try {
                System.out.println(" Service added: " + event.getInfo());
                Socket s = new Socket(serverAddress, port);
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                
                String answer = input.readLine();
                InfoTxt.setText(answer);
                } 
            
            catch (IOException ex) {
                Logger.getLogger(Client_interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void serviceRemoved(ServiceEvent se) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void serviceResolved(ServiceEvent se) {
            System.out.println("resolved");
        }
    }
    
    private void btnTvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTvActionPerformed
        
        //start service on new thread 
        TvService tvService = new TvService();
        Thread thread = new Thread(tvService);
        thread.start();
 
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

        // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new ServiceListner());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnTvActionPerformed

    private void btnAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAudioActionPerformed
       
        AudioService audioService = new AudioService();
        Thread thread = new Thread(audioService);
        thread.start();
        
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new ServiceListner());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAudioActionPerformed

    private void btnSpeakersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpeakersActionPerformed
        
        MoviesService moviesService =  new MoviesService();
        Thread thread = new Thread(moviesService);
        thread.start();
        
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new ServiceListner());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnSpeakersActionPerformed

    private void btnLightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLightsActionPerformed
        // TODO add your handling code here:
        LightsService lightsService =  new LightsService();
        Thread thread = new Thread(lightsService);
        thread.start();
        
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new ServiceListner());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnLightsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea InfoTxt;
    private javax.swing.JButton btnAudio;
    private javax.swing.JButton btnLights;
    private javax.swing.JButton btnSpeakers;
    private javax.swing.JButton btnTv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane txtDisplay;
    // End of variables declaration//GEN-END:variables
}
