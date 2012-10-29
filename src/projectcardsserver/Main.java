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
     * A naplózáshóz szükséges üzenettípusok deklarálása
     */
    private static final String typeINFO = "[INFO] ";
    private static final String typeWARNING = "[WARNING] ";
    private static final String typeERROR = "[ERROR] ";
    
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
    private static final Logger logger = new Logger(guiEnabled,servGUI);
    
      /*
       * A Main metódus
       */
      public static void main(String argv[]) throws Exception {
         
         String clientSentence; //kliens üzenete
         String capitalizedSentence; //üzenet a kliensnek
         
         /*
          * A ServerSocket létrehozása a "6789"-es portra
          */
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

