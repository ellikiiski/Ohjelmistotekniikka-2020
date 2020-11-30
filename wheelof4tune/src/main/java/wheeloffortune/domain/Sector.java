
package wheeloffortune.domain;

public class Sector {
    
    private int value;
    private SectorType type;
    
    public Sector(int value, SectorType type) {
        this.value = value;
        this.type = type;
    }
    
    public int getValue() {
        return value;
    }
    
    public SectorType getSectorType() {
        return type;
    }

    @Override
    public String toString() {
        String s = "Nyt on jotain pielessä";
        if (this.type == SectorType.MONEY) {
            s = value + "€";
        } else if (this.type == SectorType.SKIP) {
            s = "OHI-SEKTORI";
        } else if (this.type == SectorType.BANKCRUPT) {
            s = "ROSVO-SEKTORI";
        }
        return s;
    }
    
}
