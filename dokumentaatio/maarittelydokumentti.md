## Vaativuusmäärittely - Onnenpyörä

### Sovelluksen idea

Onnenpyörä eli Wheel of Fortune on hupipelailuun tarkoitettu sovellus, joka on häikäilemättömästi kopioinut konseptinsa ja nimensä suoraan tunnetusta tv-ohjelmasta.
Sovellus tulee toimimaan siten, että haluttu määrä pelaajia (2-4) osallistuu peliin ja pyörittää vuorollaan onnenpyörää, kunnes joku pelaajista arvaa kysytyn fraasin oikein.
Joka kierroksella, riippuen onnenpyörän antamasta sektorista ja siihen asti keräämästään rahasummasta, vuorossa oleva pelaaja suorittaa jonkin pelin sääntöjen mukaisen
peliliikkeen.

### Toiminnallisuudet

1. Peliin voi lisätä omia fraaseja, joita arvuutellaan myöhemmissä peleissä *TEHTY (teksti-ui)*
2. Peliä voi pelata lisäämällä vähintään kaksi pelaajaa ja aloittamalla pelin *TEHTY (teksti-ui)*
3. Pelaajien kerryttämää voittojen yhteissummaa voi tarkastella pankkitaulukosta

### Toteutus

Sovelluksella on graafinen käyttöliittymä, josta voi valita haluaako
1. lisätä uuden fraasin:
  - Käyttöliittymässä on tekstiruutu uudelle fraasille sekä drop down -valikko sen aihealueelle.
  - Uusi fraasi tallennetaan tietokantaan käyttämättömänä.
2. pelata peliä:
  - Käyttöliittymässä on tekstikentät pelaajien (2-4) nimille ja pelin aloittava Start-nappi.
  - Pelinäkymässä näkyy pelaajien nimet ja rahasummat, arvottu onnenpyörän sektori sekä arvuuteltava fraasi,
  jonka arvaamattoma kirjaimet ovat peitossa.
  - Pelilogiikka ja -säännöt määrittelevät, mitä kussakin tilanteessa on mahdollista tehdä.
  - Pelin loppuessa voittajan nimi ja voitettu summa näkyvät ruudulla ja tiedot lisätään pankkitilastoon.
  - Pelissä arvuuteltavana ollut fraasi merkitään käytetyksi.
3. tarkastella pankkitilannetta:
  - Käyttöliittymässä näkyy listaus pelaajista, jotka ovat pelanneet peliä, sekä heidän kerryttämästään rahasummasta.
  
Jos aikaa riittää, sovelluksen graafiseen ilmeeseen voi varmasti käyttää kaiken ylimääräisen ajan. Kaikista huikenta olisi jos pelinäkymään saisi ihan oikean
pyörivän onnenpyörän!
  
### Käyttäjät

Peli ei periaatteessa tarvitse kuin yhdenlaisia käyttäjiä, mutta jonkinlainen moderointi uusien fraasien lisäyksessä voisi olla varteenotettava lisäys, jos aikaa riittää.
