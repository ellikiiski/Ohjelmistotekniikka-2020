
package wheeloffortune.gamelogic;

public enum Category {
    COMMON("YLEISTIETO"),
    SCIENCE("TIEDE"),
    CULTURE("KULTTUURI");
    
    private final String name;
    
    Category(String name) {
        this.name = name;
    }
    
    public String getCategoryName() {
        return name;
    }
    
    public static Category getCategory(String name) {
        for (Category c : values()) {
            if (c.getCategoryName().equals(name.toUpperCase())) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
}
