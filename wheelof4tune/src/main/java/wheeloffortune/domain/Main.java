
package wheeloffortune.domain;

import wheeloffortune.ui.GUI;

public class Main {
    
    public static void main(String[] args) {
        
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(GUI.class);
            }
        }.start();
    }
    
}
