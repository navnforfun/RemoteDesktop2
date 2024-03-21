
package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;


public class ClientInitiator extends Thread{

    Socket socket = null;
    String ipAdress = "";
    int port = 0;

    public ClientInitiator(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
        start();
    }


    public void run(){
        DataInputStream verification = null;
        String verify = "";
        String width = "";
        String height = "";

        try {
            socket = new Socket(ipAdress,port);
            
            verification = new DataInputStream(socket.getInputStream());
            verify = verification.readUTF();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (verify.equals("valid")) {
            try {
                width = verification.readUTF();
                height = verification.readUTF();

            } catch (IOException e) {
                e.printStackTrace();
            }
            CreateFrame createFrame = new CreateFrame(socket, width, height);
         
        }
    }
    

}
