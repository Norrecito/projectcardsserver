/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import entity.DatabaseManager;

/**
 * A felhasználónév és a jelszó hitelesítését végző osztály
 * @author Norrecito
 */
public class Validator {
  
    /*
     * Ellenőrzi, hogy a számára megadott felhasználónév és jelszó helyes-e (benne van-e az adatbázisban)
     */
    public static boolean validate(UsrData usrdata){
        //Tartalom
        System.out.println("Felhasználói adatok megkapva!");
        //TESZT
        DatabaseManager.testCreate(); //Teszt sor beírása az adatbázisba
        DatabaseManager.testQuery();  //Teszt sor kiolvasása az adatbázisból
        DatabaseManager.disconnect(); //Lekapcsolódás az adatbázisról
        
        return true;
  }
}
