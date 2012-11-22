/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Norrecito
 * A felhasználóhóz tartozó információkat tároló osztály
 */
@Entity
public class UsrData implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
    /*
     * A jelszó és a felhasználónévhez tartozó szövegek deklarálása 
     */
    private String Username;
    private String Password;
    
    /*
     * A felhasználóhóz tartozó karakterlista
     * alap esetben "null", a szerver fogja visszaadni a teljes listát a hitelesítést követően
     */
    @ManyToOne
    private List<Char> chars = new ArrayList<Char>();

    /*
     * Az első konstruktor
     * Minden értéke alapértelmezetten null
     * a szerver számára
     */
    public UsrData(){
        
    }
    
    /*
     * A második konstruktor
     * Egy felhasználónevet és egy jelszót kér ami alapján beállítja az ezekhez tartozó változókat
     * a kliens számára
     */
    public UsrData(String Username, String Password){
      this.Username = Username;
      this.Password = Password;
    }
    
    /*
     * Jelszó lekérése
     */
    public String getPassword() {
        return Password;
    }
    
    /*
     * Jelszó beállítása
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    /*
     * Felhasználónév lekérése
     */
    public String getUsername() {
        return Username;
    }
    
    /*
     * Felhasználónév beállítása
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }
    
    /*
     * Karakterlista lekérése
     */
    public List<Char> getChars() {
        return chars;
    }
    
    /*
     * Karakterlista beállítása
     */
    public void getChars(List<Char> charList) {
        this.chars = charList;
    }
    
}
