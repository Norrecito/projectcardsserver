/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A naplózással foglalkozó osztály
 * @author Norrecito
 */
public class Logger {
     
    /*
     * Az idő kiíratásáhóz szükséges "Szimpla időformátum"
     */
    SimpleDateFormat time = new SimpleDateFormat("H:mm:ss");
    
    /*
     * Engedélyezve van-e a szerver grafikus kezelőfelülete
     */
    private boolean isServerGUIEnabled=true;
    
    /*
     * A szerverhez tartozó grafikus felület
     */
    private ServerGUI servgui;
    
    /*
     * Konstruktor
     */
    public Logger(boolean isServerGUIEnabled,ServerGUI servgui){
        
        this.servgui = servgui;
        this.isServerGUIEnabled = isServerGUIEnabled;
        
    }
    
    /*
     * Új napló bejegyzés kiíratása a konzolba
     */
    private void writeLogToConsole(String type, String message){
        System.out.println(getTime()+type+message);
    }
    
    /*
     * Új napló bejegyzés kiíratása a grafikus felület "Naplózás és chat részébe"
     */
    private void writeLogToServerGUI(String type, String message){
        servgui.addLog(type, message);
    }
    
    /*
     * Az új naplóbejegyzéseket fogadja el a "Main"-től
     */
    public void newLog(String type, String message){
        writeLogToConsole(type, message);
        
        /*
         * Amennyiben engedélyezve van a szerver grafikus felhasználói felülete,
         * oda is kiíratja a naplóbejegyzést
         */
        if(isServerGUIEnabled){
            writeLogToServerGUI(type, message);
        }
    }
    
    /*
     * Az aktuális idő lekérése az operációs rendszertől
     */
    private String getTime(){
        Calendar cal= Calendar.getInstance();
    return time.format(cal.getTime()+" ");  
    }
}
