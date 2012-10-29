/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    
    public ServerHandler(Config conf) throws IOException{
       
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
    public void startServer() throws IOException{
       while (!welcomeSocket.isClosed()){
           connectionSocket = welcomeSocket.accept();
           
           new Thread(new Runnable() { 
                @Override
                public void run() {
                // itt végzed a kommunikációt
                }
          }).start();
           
      } 
    }
    
    /*
     * Leállítja a szervert
     */
    public void stopServer() throws IOException{
      if(welcomeSocket.isClosed()) welcomeSocket.close();  
    }
    
    /*
     * Létrehozza a ServerSocket-et
     */
    private ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(6789); //egyenlőre teszt jellegel a "6789"-es portra, később a konfigurációból olvassa majd ki
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
    private BufferedReader createInPutStream(Socket connectionSocket) throws IOException{
       return new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
    }
    
    /*
     * Létrehozza a szerver üzeneit küldő csatornát
     */
    private DataOutputStream createOutPutStream(Socket connectionSocket) throws IOException{
      return new DataOutputStream(connectionSocket.getOutputStream());  
    }
}
