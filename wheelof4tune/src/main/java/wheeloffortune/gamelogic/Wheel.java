
package wheeloffortune.gamelogic;

import java.util.Random;

public class Wheel {
    
    private final Sector[] sectors;
    private final int maxWin;
    private int currentSector;
    
    public Wheel(int maxWin) {
        this.sectors = new Sector[24];
        this.maxWin = maxWin;
        this.initWheel();
        this.currentSector = 0;
    }
    
    //// arpoo uuden indeksin arvon muuttujaan currentSector ja palauttaa sen avulla
    //// taulukosta sectors arvotun sektorin
    public Sector spin() {
        Random rnd = new Random();
        int minSpin = sectors.length / 2;
        int variance = sectors.length / 2;
        currentSector = (currentSector + minSpin + rnd.nextInt(variance)) % sectors.length;
        return sectors[currentSector];
    }
    
    public Sector[] getSectors() {
        return sectors;
    }
    
    //// alustaa sektorit onnenpyörään eli sectors-taulukkoon
    private void initWheel() {
        for (int i = 0; i < sectors.length; i++) {
            if (i == 0 || i == 12) {
                sectors[i] = new Sector((-100 * maxWin), SectorType.BANKCRUPT);
            } else if (i == 6 || i == 18) {
                sectors[i] = new Sector(0, SectorType.SKIP);
            } else if (i == 1) {
                sectors[i] = new Sector(maxWin, SectorType.MONEY);
            } else if (i % 4 == 0) {
                sectors[i] = new Sector(200, SectorType.MONEY);
            } else {
                sectors[i] = new Sector(100, SectorType.MONEY);
            }
        }
    }
    
}
