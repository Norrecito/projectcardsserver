/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 * A szerver grafikus felhasználói felületét megjelenítő osztály, és a hozzá tartozó metódusok
 * @author Norrecito
 */
public class ServerGUI extends JFrame {
   
    /*
     * A statisztika panelhez tartozó "Játékosok" felirat
     */
    private final JLabel lbStat = new JLabel("Játékosok");
    
    /*
     * A napló panelhez tartozó "Naplózás" felirat
     */
    private final JLabel lbLog = new JLabel("Naplózás és chat");
    
    /*
     * A chat szövegmező
     */
    private final JTextField tfChat = new JTextField();
    
    /*
     * A naplózást tartalmazó szövegterület
     */
    private final JTextArea tpLog = new JTextArea();
    
    /*
     * A listáhóz tartozó listamodell
     */
    private DefaultListModel listModel = new DefaultListModel();
    
    /*
     * A szerveren lévő játékosok listáját tartalmazó lista
     */
    private JList lsCharList = new JList(listModel);
    
    /*
     * A játékos listát egy görgethető felületre rakom
     */
    private JScrollPane charlistScroller = new JScrollPane(lsCharList);
    
    /*
     * A naplózást megjelenítő szövegterületet szintén egy görgethető felületre rakom
     */
    private JScrollPane logScroller = new JScrollPane(tpLog);
    
    /*
     * A naplózást, a hozzá tartozó feliratot, és a chat mezőt megjelenítő panel
     */
    private final JPanel pnLog = new JPanel(){
        {
            setLayout(new BorderLayout()); //A panel elrendezésének beállítása
            
            /*
             * Elemek hozzáadása a panelhez
             */
            add(lbLog, BorderLayout.NORTH);
            add(logScroller, BorderLayout.CENTER);
            add(tfChat, BorderLayout.SOUTH);
        }
    };
    
    /*
     * A szerveren lévő aktív játékosok listáját, és a hozzá tartozó feliratot tartalmazó panel
     */
    private final JPanel pnStat = new JPanel(){
        {
            setLayout(new BorderLayout()); //A panel elrendezésének beállítása
            
            /*
             * Elemek hozzáadása a panelhez
             */
            add(lbStat, BorderLayout.NORTH);
            add(charlistScroller, BorderLayout.CENTER);
        }
    };
    
    
    
    /*
     * Konstruktor
     */
    public ServerGUI() {
       
       initComponents(); //A komponensek inicializálása
       initWindow();     //Az ablak inicializálása
       tpLog.setText("[INFO] Grafikus felület elindítva"); //Teszt
    }
    
    private void initComponents(){
        
        tpLog.setEditable(false); //Letiltja a szerkeszthetőségét a naplózásnak
        
        /*
         * Komponensek méretének beállítása
         */
        logScroller.setPreferredSize(new Dimension(250,250)); //A naplózást tartalmazó görgető felület méretének beállítása
        tfChat.setPreferredSize(new Dimension(250,25)); //A chat mező méretének beállítása
        charlistScroller.setPreferredSize(new Dimension(120,250)); //A játékos listát tartalmaző görgető felület méretének beállítása
     }
    
    private void initWindow(){
      
      setTitle("Project Cards szerver"); //Az ablak cimkéjének beállítása    
      setIconImage(RM.getIconImage()); //Az ablak ikonjának beállítása
      setLayout(new BorderLayout()); //Elrendezés beállítása 
      
      /*
       * Elemek hozzáadása az ablakhoz
       */
      add(pnStat, BorderLayout.WEST);
      add(pnLog, BorderLayout.CENTER);
      
      setVisible(true); //Legyen látható
      setDefaultCloseOperation(EXIT_ON_CLOSE); //Az X-re kattintva bezárodik a program
      pack(); //Tönörítse az ablakot az komponensek méretétől függően
      setLocationRelativeTo(null); //Az ablak középen jelenjen meg
      setResizable(false); //Nem lehet átméretezni
    }
    
    /*
     * Új naplóbejegyzés felvitelére szolgáló metódus
     */
    public void addLog(String type, String message){
       tpLog.append(type+message+"\n"); 
    }
}
