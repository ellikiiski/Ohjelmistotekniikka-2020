
package wheeloffortune.test;

import wheeloffortune.gamelogic.Sector;
import wheeloffortune.gamelogic.SectorType;

public class FakeWheel {
    
    private final Sector[] sectors;
    private final int maxWin;
    private int currentSector;

    public FakeWheel(int maxWin) {
        this.sectors = new Sector[4];
        this.maxWin = maxWin;
        this.initWheel();
        this.currentSector = -1;
    }

    //// ei arvo seuraavaa sektoria vaan käy sektorit läpi järjestyksessä
    //// 100€
    //// ohi-sektori
    //// rosvo-sektori
    //// maksimivoitto
    //// takaisin 100€
    public Sector spin() {
        currentSector = (currentSector + 1) % sectors.length;
        return sectors[currentSector];
    }

    public Sector[] getSectors() {
        return sectors;
    }

    //// alustaa sektorit testausta varten
    private void initWheel() {
        sectors[0] = new Sector(100, SectorType.MONEY);
        sectors[1] = new Sector(0, SectorType.SKIP);
        sectors[2] = new Sector((-100 * maxWin), SectorType.BANKCRUPT);
        sectors[3] = new Sector(maxWin, SectorType.MONEY);
    }
    
}
