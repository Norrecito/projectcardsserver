/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A szerver indításáért, illetve leállításáért felelős osztály
 * @author Norrecito
 */
public class ServerHandler {
  
    /*
     * A konfiguráció
     */
    Config conf;
    
    /*
     * Segédváltozók
     */
    private String clientSentence; //kliens üzenete
    private String capitalizedSentence; //üzenet a kliensnek
    
    /*
     * A kapcsolathóz szükséges Socket és ServerSocket, később inicialízálásra kerülnek 
     */
    private Socket connectionSocket;
    private ServerSocket welcomeSocket;
    
    /*
     * A ki és bemeneti csatornáhóz szükséges objektumok
     */
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    
    public ServerHandler(Config conf){
       
        this.conf = conf; //a konfiguráció beállítása
        
        /*
         * Socket és ServerSocket létrehozása
         */
        connectionSocket = createSocket();
        welcomeSocket = createServerSocket();
        
        /*
         * Ki és bemeneti csatorna létrehozása
         */
        inFromClient = createInPutStream(connectionSocket);
        outToClient = createOutPutStream(connectionSocket);
        
    }
    
    /*
     * Elindítja a szervert
     */
    public void startServer() {
       while (!welcomeSocket.isClosed()){
            try {
                connectionSocket = welcomeSocket.accept();
                clientSentence = inFromClient.readUTF();
                System.out.println("Üzenet klienstől: " + clientSentence);
                capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
               /* new Thread(new Runnable() { 
                     @Override
                     public void run() {
                     // itt végzed a kommunikációt
                     }
               }).start();
               * 
               */
            } catch (IOException ex) {
               /*
                * Üzen a naplózásnak amennyiben valamilyen okból a szerver indítása sikertelen volt
                */
                ServerLogger.newLog(3, "A szerver elindítása nem sikerült!");
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
           
      } 
    }
    
    /*
     * Leállítja a szervert
     */
    public void stopServer(){
      if(!welcomeSocket.isClosed()) try {
            welcomeSocket.close();
        } catch (IOException ex) {
            /*
             * Üzen a naplózásnak amennyiben valamilyen okból a szerver leállítása sikertelen volt
             */
            ServerLogger.newLog(3, "A szerver leállítása sikertelen!");
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /*
     * Létrehozza a ServerSocket-et
     */
    private ServerSocket createServerSocket(){
        try {
            return new ServerSocket(6789); //egyenlőre teszt jellegel a "6789"-es portra, később a konfigurációból olvassa majd ki
        } catch (IOException ex) {
            /*
             * Ha nem sikerült létrehozni a ServerSocket-et üzen a naplózásnak
             */
            ServerLogger.newLog(3, "Nem sikerült létrehozni a ServerSocket-et!");
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
         
    }
    
    /*
     * Létrehozza a Socket-et
     */
    private Socket createSocket(){
        return new Socket();
    }
    
    /*
     * Létrehozza kliens üzeneteit fogadó csatornát
     */
    private ObjectInputStream createInPutStream(Socket connectionSocket){
        try {
            return new ObjectInputStream(connectionSocket.getInputStream());
        } catch (IOException ex) {
            /*
             * Ha nem sikerül létrehozni az adatcsatornát üzen a naplózásnak
             */
            ServerLogger.newLog(3, "Nem sikerült létrehozni a bemeneti adatcsatornát!");
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    /*
     * Létrehozza a szerver üzeneit küldő csatornát
     */
    private ObjectOutputStream createOutPutStream(Socket connectionSocket){
        try {
            return new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException ex) {
            /*
             * Ha nem sikerül létrehozni az adatcsatornát üzen a naplózásnak
             */
            ServerLogger.newLog(3, "Nem sikerült létrehozni a kimeneti adatcsatornát!");
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
