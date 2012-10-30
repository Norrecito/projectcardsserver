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

    /*
     * A szerver konfigurációja
     */
    private static final Config conf = Config.getInstance();
    
    /*
     * Engedélyezve van-e a szerverhez tartozó grafikus felület
     */
    private static boolean guiEnabled=true;
    
    /*
     * A szerverhez tartozó grafikus felhasználói felület
     */
    private static final ServerGUI servGUI = new ServerGUI();
    
    /*
     * A naplózással foglalkozó osztály
     */
    private static final ServerLogger logger = new ServerLogger(guiEnabled,servGUI);
    
    /*
     * A szerver indítását illetve leállítását végző osztály
     */
    private static final ServerHandler servhandler= new ServerHandler(conf);
    
      /*
       * A Main metódus
       */
      public static void main(String argv[]) throws Exception {
         
         servhandler.startServer();
         
         /*
         String clientSentence; //kliens üzenete
         String capitalizedSentence; //üzenet a kliensnek
         
         
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
         * 
         */
         
      }
    }

