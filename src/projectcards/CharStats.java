/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

/**
 * A játékos karakterekhez tartozó statisztika
 * -minden példány (elkészült karakter) saját hozzá tartozó statisztikával rendelkezik
 * 
 * @author Norrecito
 */
public class CharStats {
    
    /*
     * Általános (General)
     * Az általános a karakterre vonatkozó statisztikák:
     * @param kills:        az összes idáig legyőzőtt ellenfél (kártya)
     * @param deaths:       az összes idáig legyőzőtt lényed (kártyád)
     * @param totalgold:    az összes idáig összegyüjtött arany
     * @param timeplayed:   az idáig lejátszott idő a karakterrel (órában)
     */
    private int kills=0, deaths=0, totalgold=0; 
    private long timeplayed=0;
    
    /*
     * Harc (Combat)
     * A harcal kapcsolatos statisztikák:
     * @param highesthitdone:       a legnagyobb idáig bevitt sebzés
     * @param highesthitreceived:   a legnagyobb idáig kapott sebzés
     * @param totaldamagedone:      az összes idáig bevitt sebzés
     * @param totaldamagereceived:  az összes idáig kapott sebzés
     */
    private int highesthitdone=0, hightesthitreceived=0, totaldamagedone=0, totaldamagerecieved=0;
    
    /*
     * Küldetés (Quest)
     * A játékban szereplő küldetésekkel kapcsolatos statisztikák:
     * @param questtaken:       az összes idáig elválalt küldetés
     * @param questcompleted:   az összes idáig teljesített (sikeres) küldetés
     * @param questfailed:      az összes idáig elbukott (sikertelen) küldetés
     */
    private int  questtaken=0,questcompleted=0, questfailed=0;
    
    /*
     * Kártyák (Cards)
     * A játékban fellelhető kártyákkal kapcsolatos statisztikák:
     * @param cardscollected:   az összes idáig összegyüjtött kártyalap
     * @param currentcards:     a jelenleg a karakternél lévő kártya
     */
    private int cardscollected=0, currentcards=0;
    
    /*
     * PVP (Player versus Player) "Játékos Játékos ellen"
     * A Játékos-Játékos elleni harcal kapcsolatos statisztikák:
     * @param duelsplayed:  az összes idáig játékosok ellen lejátszott párbaj
     * @param dualwins:     az összes idáig játékosok ellen megnyert párbaj
     * @param duellosses:   az összes idáig játékosok ellen elvesztett párbaj
     * @param winloseratio: a győzelem/vereség aránya az idáig lejátszott párbajok alapján
     */
    private int duelsplayed=0, duelwins=0, duellosses=0;
    private double winloseratio=0;
    
    /*
     * Konstruktor
     */
    public CharStats(){
        
    }
    
    /*
     * Az osztályhóz tartozó "Set"-erek és "Get"-erek
     */

    public int getCardscollected() {
        return cardscollected;
    }

    public void setCardscollected(int cardscollected) {
        this.cardscollected = cardscollected;
    }

    public int getCurrentcards() {
        return currentcards;
    }

    public void setCurrentcards(int currentcards) {
        this.currentcards = currentcards;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getDuellosses() {
        return duellosses;
    }

    public void setDuellosses(int duellosses) {
        this.duellosses = duellosses;
    }

    public int getDuelsplayed() {
        return duelsplayed;
    }

    public void setDuelsplayed(int duelsplayed) {
        this.duelsplayed = duelsplayed;
    }

    public int getDuelwins() {
        return duelwins;
    }

    public void setDuelwins(int duelwins) {
        this.duelwins = duelwins;
    }

    public int getHighesthitdone() {
        return highesthitdone;
    }

    public void setHighesthitdone(int highesthitdone) {
        this.highesthitdone = highesthitdone;
    }

    public int getHightesthitreceived() {
        return hightesthitreceived;
    }

    public void setHightesthitreceived(int hightesthitreceived) {
        this.hightesthitreceived = hightesthitreceived;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getQuestcompleted() {
        return questcompleted;
    }

    public void setQuestcompleted(int questcompleted) {
        this.questcompleted = questcompleted;
    }

    public int getQuestfailed() {
        return questfailed;
    }

    public void setQuestfailed(int questfailed) {
        this.questfailed = questfailed;
    }

    public int getQuesttaken() {
        return questtaken;
    }

    public void setQuesttaken(int questtaken) {
        this.questtaken = questtaken;
    }

    public long getTimeplayed() {
        return timeplayed;
    }

    public void setTimeplayed(long timeplayed) {
        this.timeplayed = timeplayed;
    }

    public int getTotaldamagedone() {
        return totaldamagedone;
    }

    public void setTotaldamagedone(int totaldamagedone) {
        this.totaldamagedone = totaldamagedone;
    }

    public int getTotaldamagerecieved() {
        return totaldamagerecieved;
    }

    public void setTotaldamagerecieved(int totaldamagerecieved) {
        this.totaldamagerecieved = totaldamagerecieved;
    }

    public int getTotalgold() {
        return totalgold;
    }

    public void setTotalgold(int totalgold) {
        this.totalgold = totalgold;
    }

    public double getWinloseratio() {
        return winloseratio;
    }

    public void setWinloseratio(double winloseratio) {
        this.winloseratio = winloseratio;
    }
    
}
