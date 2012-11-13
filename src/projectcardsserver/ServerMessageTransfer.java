/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.net.Socket;

/**
 * A szerverhez tartozó üzenetek küldését, és fogadását végző osztály
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
        
        if(o instanceof String){
         //Amenyiben az üzenet String, kiírja a konzolba   
         System.out.println(o.toString());    
        } else if(o instanceof UsrData) {
            //Amennyiben az üzenet egy UsrData típusú objektum, elküldi validálásra (hitelesítésre) a megfelelő osztálynak
            Validator.validate((UsrData)o);
        }
        
    }
    
    /*
     * Induláskor
     */
    @Override
    protected void onStart(){
       sendMessage("szerver üzeni, hogy asdfgh"); 
    }
}
