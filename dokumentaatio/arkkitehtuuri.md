## Arkkitehtuuri

### Karkea rakenne

Ohjelmassa on kolme tasoa ikäänkuin päällekkäin: ylhäältä alas käyttöliittymä, sovelluslogiikka, tietokannat.

![luokkakaavio alustava](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/arkkitehtuuri.jpg)

### Käyttöliittymä

Käyttöliittymään liittyvät komponentit löytyvät pakkauksesta <em>wheeloffortune.ui</em>.

Käyttöliittymässä on aloitusnäyttö, josta pääsee kolmeen päähaaraan:
1. Pelaajastatistiikkanäkymään (ei tosin vielä toteutettu)
2. Lisäämään uuden fraasin tietokantaan
3. Pelaamaan peliä (vajaasti toteutettu)

Jokainen näistä on toteutettu rajapinnan <em>View</em> implementoivana luokkana, jotka paketoivat toiminnalisuuksiaan, jottei koodista tulisi pelkkää spagettia.
Pelinäkymä on muita näkymiä huomattavasti monimutkaisempi ja koostuukin parista eri näkymästä <em>AddPlayerView</em> ja <em>GameView</em>.

#### Pelinäkymä

Pelinäkymä aukeaa ensin näkymään, jossa lisätään pelaajat. Tämän jälkeen päästään varsinaiseen pelinäkymään <em>GameView</em>, joka puolestaan on rakennettu useista <em>Layout</em>-rajapinnan implementoivista luokista, jälleen selkeyden parantamiseksi. Kukin <em>Layout</em>-luokista huolehtii yhdestä osasta käyttöliittymän pelinäkymän toiminnallisuutta, ja tavoitteena on tietenkin saada ne toimimaan saumattomasti yhteen.

### Sovelluslogiikka

Sovelluslogiikkaan liittyvät komponentit löytyvät pakkauksesta <em>wheeloffortune.domain</em>.

Sovelluslogiikka pyörii lähinnä <em>Game</em>-luokassa ja muut luokat ovat tavallaan apuolioita pelin pyörittämisessä. Oleellisesti peli sisältää kolme <em>Player</em>-olioita, yhden <em>Wheel</em>-olion ja yhden <em>Phrase</em>-olion. Muut "pienemmät" luokat ovat edelleen edellä mainittujen "apuolioita".

### Pysyväistallennus

Pysyväistallennukseen liittyvät komponentit löytyvät pakkauksesta <em>wheeloffortune.dao</em>.

Ohjelma käyttää hyväkseen kahta tietokantaa: pelaajatietokanta (<em>PlayerDao</em>) ja fraasitietokanta (<em>PhraseDao</em>). Tiedot tallennetaan tiedostoihin <em>PlayerDB.txt</em> ja <em>PhraseDB.txt</em>, joista ne myös tarpeen tullen luetaan.

### Esimerkki toiminnallisuudesta

Alla sekvenssikaavio onnenpyörän pyörittämisestä.

![sekvenssikaavio spin](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/sekvenssikaavio.png)
