/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * A szerver erőforrás kezelő osztálya
 * @author Norrecito
 */
public class RM {
  
   /*
    * Visszaadja a program ikonját
   */
    public static Image getIconImage() {
        return getImage("icon.png");
    }
    
  /*
   * A képfájlok beolvasását szolgáló metódus, ezután "Image" formában lesznek
   */
    private static Image getImage(String name) {
        try {
            return Toolkit.getDefaultToolkit().getImage("resources/"+name);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }  
}
