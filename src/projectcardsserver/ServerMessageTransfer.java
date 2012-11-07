/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.net.Socket;

/**
 *
 * @author Norrecito
 */
public class ServerMessageTransfer extends MsgTransfer {
    
    /*
     * Konstruktor
     */
    public ServerMessageTransfer(Socket clientsocket){
            super(clientsocket);  
        }
    
    /*
     * Üzenet érkezésekor
     */
    @Override
    protected void onMessage(Object o){
        System.out.println(o.toString()); 
    }
    
    @Override
    protected void onStart(){
       sendMessage("szerver üzeni, hogy asdfgh"); 
    }
}
