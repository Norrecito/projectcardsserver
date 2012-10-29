/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Itt még miden erőssen teszt jellegü
 * @author Norrecito
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    /*
     * A naplózáshóz szükséges üzenettípusok deklarálása
     */
    private String typeINFO = "[INFO] ";
    private String typeWARNING = "[WARNING] ";
    private String typeERROR = "[ERROR] ";
    
    /*
     * Engedélyezve van-e a szerverhez tartozó grafikus felület
     */
    private boolean guiEnabled=true;
    
    /*
     * A szerverhez tartozó grafikus felhasználói felület
     */
    private static final ServerGUI servGUI = new ServerGUI();
    
    
      public static void main(String argv[]) throws Exception {
         
         String clientSentence;
         String capitalizedSentence;
         ServerSocket welcomeSocket = new ServerSocket(6789);

         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Üzenet klienstől: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
         }
      }
    }

