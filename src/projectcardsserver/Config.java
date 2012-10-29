/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A szerverhez tartozó konfiguráció
 * @author Norrecito
 */
public class Config {
   
  /*
   * A később értékeket tároló "Map" 
   */
   private Map<String, String> values;
    
   /*
    * Az alapértelmezett beállítások deklarálása
    */
    private static int Port =6789;
    
   /*
    * Sortörés jel lekérése és egy változóba deklarálása
    */
    private static final String LS = System.getProperty("line.separator");
    
   /*
    * Komment (megjegyzés) kezdetét jelző karakter
    */
    private static final String CC = "#";
    
   /*
    * A fájl ahova mentésre kerülnek a beállítások
    */
    private static final File CFile = new File("Config.ini");
    
   /*
    * Az alapértelmezett szerveroldali konfigurációs fájl tartalma
    * jelenleg még csak a megjegyzéseket és a szerver portját tartalmazza
    */
    private static final String DefaultConfig=
            CC+" Ez a fájl a Projectcardx néven futó alkalmazás szerver részének konfigurációját tartalmazza"+LS+
            "Port:6789"+CC+" A port amin a szerver figyel"+LS+
            CC+" Ez a konfigurációs fájl a későbbieknek jelentősen bővül ujjabb a szerverre vonatkozó beállítási lehetőségekkel";
    
    /*
     * Visszaadja a portot amin a szerver figyel
     */
    public Integer getPort(){
        /*
         * Amennyiben a fájlban szereplő port értéke nem esik a megfelelő tartományba, null értéket ad vissza
         */
        if (Port < 1 || Port > 65535) return null;
            return Port;
        }
    
    
    /*
     * Az értékeket visszaadó metódus (konfigurációs fájl értékei)
     */
    public Map<String, String> getValues() {
        return values;
    }
    
    /*
     * Ellenőrzi helyes-e a konfiguráció
     */
    public boolean isCorrect(){
        return getPort() != null;
    }
    
    /*
     * Fájlból beolvassa és létrehozza a konfigurációs objektumot
     */
    public static Config getInstance(){
        Config config = new Config();
        List<String> conf = read(CFile, DefaultConfig);
        if(conf != null) {
            int ind;
            String key, val;
            config.values = new HashMap<String, String>();
            for(String ln: conf) {
                ind = ln.indexOf(" ");
                if (ind == -1) continue;
                key = ln.substring(0, ind);
                val = ln.substring(ind).trim();
                config.values.put(key, val);
            }
        }
        return config;
    }
    
    /*
     * A konfigurációs fájl beolvasását végző metódus
     */
    private static List<String> read(File file, String def){
        BufferedReader in = null;
        try {
            int ind;
            String ln;
            List<String> ls = new ArrayList<String>();
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while((ln = in.readLine()) != null){
                ln = ln.trim();
                if (ln.startsWith(CC)) continue;
                ind = ln.indexOf(CC);
                if (ind != -1) ln = ln.substring(0, ind);
                if (!ln.isEmpty()) ls.add(ln);
            }
            in.close();
            return ls;
        }       
         catch (FileNotFoundException ex) {
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                out.write(def, 0, def.length());
                out.flush();
                out.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return null;
    
} 
    
}
