/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import projectcards.Cast;

/**
 * Az adatbázissal kapcsolatos teendőket elvégző osztály
 * @author Norrecito
 */
public class DatabaseManager {
    
    /*
     * Az adatbázishóz kapcsolódó EntityManagerFactory és EntityManager létrehozása
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("database/projectcards.odb");
    private static EntityManager em = emf.createEntityManager();
    
    /*
     * Az adatbázis teszteléséhez szükséges "gyártó" metódus
     * lényegében egy teszt sort kreál, amit elment az adatbázisba
     */
    public static void testCreate(){
      
        UsrData u = new UsrData("Norrecito","valami");  //Új felhasználó gyártása (Felhasználónév, Jelszó)
        Char c = new Char("char1", Cast.ARCHER);        //Új karakter gyártása (Név, Kaszt)
        u.getChars().add(c);                         //A karakter hozzáadása a felhasználó karakterlistájáhóz
        c.setLvl(100);                                  //A karakter szintjének beállítása
        c.getStats().setKills(10000);                   //A karakterhez tartozó statisztikában a legyőzőtt ellenfelek beállítása
        
        /*
         * A fent kreált adatok adatbázisba mentésének megkezdése majd végrehajtása
         */
        em.getTransaction().begin();
        em.persist(c);
        em.persist(u);
        em.getTransaction().commit();
    }
    
    /*
     * Az adatbázis teszteléséhez szükséges "lekérdező" metódus
     * lényegében a fent legyártott adatokat kérdezi le az adatbázisból, hogy sikerült-e a mentésük
     * illetve vissza lehet-e őket sikeresen olvasni az adatbázisból
     */
    public static void testQuery(){
       
        TypedQuery<UsrData> query = em.createQuery("select u from UsrData u", UsrData.class); //A lekérdezés deklarálása
        List<UsrData> results = query.getResultList(); //A lekérdezés eredményének elmentése egy listába
        
        /*
         * Az eredmények kiíratása konzolba
         */
        for (UsrData u : results){
           System.out.println("name: " + u.getUsername());
           Char firstChar = u.getChars().get(0);
           System.out.println("first character: " + firstChar.getCast());
           System.out.println("kills: " + firstChar.getStats().getKills());
           System.out.println("OneToMany test: " + firstChar.getUser().getUsername()); 
        }
    }
    
    /*
     * Az adatbázisról való lekapcsolódást elvégző metódus
     */
    public static void disconnect(){
        em.close();
        emf.close();
    }
}
