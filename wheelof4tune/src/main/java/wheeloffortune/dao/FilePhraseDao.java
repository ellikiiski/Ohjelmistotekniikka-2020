
package wheeloffortune.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import wheeloffortune.gamelogic.Category;
import wheeloffortune.gamelogic.Phrase;

public class FilePhraseDao implements PhraseDao {
    
    private final ArrayList<Phrase> phrases;
    private final String file;
    
    public FilePhraseDao(String fileName) {
        this.file = fileName;
        /*String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (Exception e) {
            System.out.println(e);
        }
        file = path + "\\src\\main\\resources\\" + fileName;*/
        phrases = new ArrayList<>();
        initPhrases();
    }
    
    @Override
    public Phrase create(Phrase phrase) throws Exception {
        phrases.add(phrase);
        save();
        return phrase;
    }
    
    @Override
    public Phrase getRandomPhrase() {
        try {
            Random random = new Random();
            int i = random.nextInt(phrases.size());
            Phrase p = phrases.get(i);
            p.addUse();
            save();
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Phrase> getAll() {
        return phrases;
    }
    
    //// apumetodi fraasilistan luomiseen
    private void initPhrases() {
        InputStream s = getClass().getClassLoader().getResourceAsStream(file);
        InputStreamReader sReader = new InputStreamReader(s);
        BufferedReader reader = new BufferedReader(sReader);
        try {
            //Scanner reader = new Scanner(new File(file));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("\t");
                Phrase p = new Phrase(parts[0], Category.getCategory(parts[1]), Integer.valueOf(parts[2]));
                phrases.add(p);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("InitPhrases: " + e);
        }
    }
    
    //// tallentaa fraasin tiedot tiedostoon
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Phrase phrase : phrases) {
                writer.write(phrase.getPhrase() + "\t" + phrase.getCategory().getName() + "\t" + phrase.getUses() + "\n");
            }
        }
    }
}
