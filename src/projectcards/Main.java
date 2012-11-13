/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

/**
 * Itt még miden erőssen teszt jellegü
 * @author Norrecito
 */
public class Main {

    /*
     * A szerver konfigurációja
     */
    private static final Config conf = Config.getInstance();
    
    /*
     * Engedélyezve van-e a szerverhez tartozó grafikus felület
     */
    private static boolean guiEnabled=true;
    
    /*
     * A szerverhez tartozó grafikus felhasználói felület
     */
    private static final ServerGUI servGUI = new ServerGUI();
    
    /*
     * A naplózással foglalkozó osztály
     */
    private static final ServerLogger logger = new ServerLogger(servGUI);
    
    /*
     * A szerver indítását illetve leállítását végző osztály
     */
    private static ServerHandler servhandler= new ServerHandler(conf);
    
      /*
       * A Main metódus
       */
      public static void main(String argv[]) throws Exception {
         
         setOptions(argv);
         servhandler.startServer();
         
        }
      
      /*
       * A konzolból beolvasott indítási paraméterek alapján elvégzi a megfelelő beállításokat
       * a következő indítási paraméterek lehetségesek (melletük a kiváltott hatással):
       * "-go" (GUI Off): a szerverhez tartozó grafikus felület nem lesz látható futás alatt
       * "-lo" (Log Off): a konzolra történő naplóbejegyzések kiírását szünteti meg
       */
      private static void setOptions(String[] args){
        if(args.length == 1) {
          if(args[0].equals("-go")) {
              servGUI.Enabled(false);
          } 
          else if(args[0].equals("-lo")){
              ServerLogger.Enabled(false);
          }
        } 
      }
    }

