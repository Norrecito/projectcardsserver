/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import entity.DatabaseManager;
import java.util.List;

/**
 * A felhasználónév és a jelszó hitelesítését végző osztály
 * @author Norrecito
 */
public class Validator {
  
    /*
     * Ellenőrzi, hogy a számára megadott felhasználónév és jelszó helyes-e (benne van-e az adatbázisban)
     */
    public static boolean validate(UsrData usrdata){
        
        System.out.println("Felhasználói adatok megkapva!"); //kiírja konzolba hogy sikeresen megkapta a várt paramétereket
        //TESZT
        System.out.println(usrdata.getUsername());
        System.out.println(usrdata.getPassword());
        /*
        DatabaseManager.testCreate(); //Teszt sor beírása az adatbázisba
        DatabaseManager.testQuery();  //Teszt sor kiolvasása az adatbázisból
        */
        List<entity.UsrData> users = DatabaseManager.getUsers(); //Elkéri a felhasználok listáját az adatbáziskezelőtől
        for (entity.UsrData u : users){
           if((u.getUsername() == null ? usrdata.getUsername() == null : u.getUsername().equals(usrdata.getUsername())) && (u.getPassword() == null ? usrdata.getPassword() == null : u.getPassword().equals(usrdata.getPassword()))){
               //Amenyiben a felhasználó szerepel az adatbázisban és a hozzá tartozó jelszó is helyes, igaz értéket ad vissza
               return true; 
           } 
        }
       DatabaseManager.disconnect(); //Lekapcsolódás az adatbázisról
       return false; 
       }
}
