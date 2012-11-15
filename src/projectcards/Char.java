/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;



/**
 * Az általános karakter osztály
 * 
 * @author Norrecito
 */

public class Char  {
    
    /*
     * A karakter neve
     */
    private String name;
    
    /*
     * A karakter szintje
     */
    private int lvl=1;
    
    /*
     * A karakter jelenlegi osztálya
     */
    private Cast cast;
    
    /*
     * A karakterhez tartozó statisztika
     */
    private CharStats stats;
    
    /*
     * A karakterhez tartozó avatar
     */
    private Avatar avatar;
    
    /*
     * A felhasználó akihez a karakter tartozik
     */
    private UsrData user;
    
    /*
     * A jelenlegi aktuális szinthez tartozó megszerzett tapasztalatpont
     */
    private int xp;
    
    /*
     * Az összes megszerzett tapasztalatpont
     */
    private int totalxp;
    
    /*
     * A statisztikáhóz szükséges alapvető változók:
     * @param STR: Strenght     = Erő
     * @param INT: Intelligence = Intelligencia
     * @param DEX: Dexterity    = Ügyesség
     * @param VIT: Vitality     = Életerő
     */
    private int STR, INT, DEX, VIT;
    
    /*
     * A konstruktor
     */
    public Char(){
        
    }
    
    /*
     * A második konstruktor
     */
    public Char(String name, Cast cast){
       this.name = name;
       this.cast = cast;
       this.stats = new CharStats(); 
    }
    
    /*
     * Az osztályhóz tartozó "Set"-erek és "Get"-erek
     */
    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getDEX() {
        return DEX;
    }

    public int getINT() {
        return INT;
    }

    public int getSTR() {
        return STR;
    }

    public int getVIT() {
        return VIT;
    }

    public Cast getCast() {
        return cast;
    }

    public String getName() {
        return name;
    }

    public CharStats getStats() {
        return stats;
    }

    public int getTotalxp() {
        return totalxp;
    }

    public UsrData getUser() {
        return user;
    }

    public int getXp() {
        return xp;
    }
    
}
