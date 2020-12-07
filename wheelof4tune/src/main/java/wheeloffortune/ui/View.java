
package wheeloffortune.ui;

import javafx.scene.Scene;

public interface View {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.
    
    Scene getScene();
    
}
