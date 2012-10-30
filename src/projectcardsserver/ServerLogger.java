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
public class ServerLogger {
     
    /*
     * Engedélyezve van-e 
     */
    private static boolean isEnabled=true;
    
    /*
     * Az idő kiíratásáhóz szükséges "Szimpla időformátum"
     */
    private static SimpleDateFormat time = new SimpleDateFormat("H:mm:ss");
    
    /*
     * A naplózáshóz szükséges üzenettípusok deklarálása
     */
    private static final String typeINFO = "[INFO] ";
    private static final String typeWARNING = "[WARNING] ";
    private static final String typeERROR = "[ERROR] ";
    
    /*
     * Engedélyezve van-e a szerver grafikus kezelőfelülete
     */
    private static boolean isServerGUIEnabled=true;
    
    /*
     * A szerverhez tartozó grafikus felület
     */
    private static ServerGUI servgui;
    
    /*
     * Konstruktor
     */
    public ServerLogger(ServerGUI servgui){
        
        this.servgui = servgui;
        
    }
    
    /*
     * Új napló bejegyzés kiíratása a konzolba
     */
    private static void writeLogToConsole(String type, String message){
        System.out.println(getTime()+type+message+"\n");
    }
    
    /*
     * Új napló bejegyzés kiíratása a grafikus felület "Naplózás és chat részébe"
     */
    private static void writeLogToServerGUI(String type, String message){
        servgui.addLog(type, message);
    }
    
    /*
     * Az új naplóbejegyzéseket fogadja el a "Main"-től
     */
    public static void newLog(int typeNumber, String message){
        
        String type="";
        /*
         * A típus sorszáma alapján kiválasztja a hozzátartozó bejegyzéstípust
         */
        switch(typeNumber){
            case 1: type=typeINFO;
            break;
            case 2: type=typeWARNING;
            break;
            case 3: type=typeERROR;
            break;
    }
        if(isEnabled) {
          writeLogToConsole(type, message);
        
        /*
         * Amennyiben engedélyezve van a szerver grafikus felhasználói felülete,
         * oda is kiíratja a naplóbejegyzést
         */
         if(isServerGUIEnabled){
            writeLogToServerGUI(type, message);
         }   
        }
        
    }
    
    /*
     * Az aktuális idő lekérése az operációs rendszertől
     */
    private static String getTime(){
        Calendar cal= Calendar.getInstance();
    return time.format(cal.getTime());  
    }
    
    /*
     * Engedélyezve van-e a naplózás
     */
    public static void Enabled(boolean enabled) {
       isEnabled=enabled; 
    }
}
