## Vaativuusmäärittely - Onnenpyörä

### Sovelluksen idea

Onnenpyörä eli Wheel of Fortune on hupipelailuun tarkoitettu sovellus, joka on häikäilemättömästi kopioinut konseptinsa ja nimensä suoraan tunnetusta tv-ohjelmasta.
Ohjelma toimii siten, että 3 pelaajaa osallistuu peliin ja pyörittää vuorollaan onnenpyörää ja arvaa konsonantteja tai ostaa vokaaleja kunnes joku pelaajista arvaa kysytyn fraasin oikein.
Joka kierroksella, riippuen onnenpyörän antamasta sektorista ja siihen asti keräämästään rahasummasta, vuorossa oleva pelaaja suorittaa jonkin pelin sääntöjen mukaisen
peliliikkeen.

### Toiminnallisuudet

1. Peliin voi lisätä omia fraaseja, joita arvuutellaan myöhemmissä peleissä
2. Peliä voi pelata lisäämällä kolme pelaajaa ja aloittamalla pelin, jolloin tietokannasta arvotaan yksi fraasi pelin tehtäväksi
3. Pelaajien pankkitilannetta (eli siihen astista peleistä yhteensä voitettua rahasummaa) voi tarkastella pankkitaulukosta

### Toteutus

Sovelluksella on graafinen käyttöliittymä, josta voi valita haluaako
1. lisätä uuden fraasin:
  - Käyttöliittymässä on tekstiruutu uudelle fraasille sekä checkboxeja, joista voi valita fraasin kategorian.
  - Uusi fraasi tallennetaan tietokantaan käyttämättömänä.
2. pelata peliä:
  - Ensin näkyviin tulee kolme tekstikenttää pelaajien nimille ja pelin aloittava starttinappi.
  - Pelinäkymässä näkyy pelaajien nimet ja rahasummat, viimeisin arvottu onnenpyörän sektori sekä arvuuteltava fraasi,
  jonka arvaamattoma kirjaimet ovat peitossa. Lisäksi pelinäkymässä on napit kullekin mahdolliselle peliliikkeelle sekä vaihtuva teksti, jossa kerrotaan aina edellinen pelitapahtuma ja sillä hetkellä vuorossa oleva pelaaja.
  - Pelilogiikka ja -säännöt määrittelevät, mitä kussakin tilanteessa on mahdollista tehdä.
  - Pelin loppuessa voittajan nimi ja voitettu summa näkyvät ruudulla ja tiedot lisätään pankkitilastoon.
  - Pelissä arvuuteltavana olleen fraasin käytetty-arvoa lisätään yhdellä (oikeasti tämä tapahtuu jo pelin käynnistyessä, eli fraasi tulee käytetyksi, vaikka peli keskeytettäisiin).
3. tarkastella pankkitilannetta:
  - Käyttöliittymässä näkyy listaus pelaajista, jotka ovat pelanneet peliä, sekä heidän kerryttämästään rahasummasta. Listauksessa ylimpänä on eniten rahaa pankkiin kerännyt pelaaja jne.
  
Visuaalisesti käyttöliittymä on varsin ankea ja vaatimaton, mutta itse pelin pyörittäminen toimii loistavasti.
  
### Käyttäjät

Pelin käyttäjät ovat pelaajia, jotka ovat kaikki tasa-arvoisia keskenään. Minkäänlaista kirjautumista ei vaadita.
