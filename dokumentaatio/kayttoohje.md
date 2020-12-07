## Käyttöohje - Onnenpyörä

### Versiot

Ohjelmasta on toistaiseksi kaksi versiota käyttöliittymän mukaan:
- Tekstikäyttöliittymä: sisältää hieman enemmän toiminnallisuutta, mutta on luonnollisesti kömpelömpi käytettävä
- Graafinen käyttöliittymä: kaikkea toiminnallisuutta en ole vielä kerennyt tässä toteuttaa, mutta toimivilta osiltaan GUI on tietysti miellyttävämpi käyttää

Keskityn tässä käyttöohjeessa graafisen käyttöliittymän versioon, koska tekstikäyttöliittymästä on tarkoitus luopua heti,
kun loputkin toiminnallisuudet on saatu toteutettu GUI:ssa.

### Konfigurointi

Kumpikin ohjelman versio olettaa, että käynnistyskansiossa on tiedostot <em>PlayerDB.txt</em> ja <em>PhraseDB.txt</em>, joista ainakin jälkimmäisessä tulee olla vähintään
yksi validi "tietokantarivi".

### Ohjelman käynnistäminen

Käynnistäminen onnistuu komentoriviltä komennolla

LISÄÄÄÄÄ TÄHÄN KOMENTO

#### Aloitusnäkymä

Aloitusnäkymässä käyttäjällä on kolme vaihtoehtoa:
1. Tarkastella pelaajastatistiikkoja (ei vielä toteutettu)
2. Lisätä uuden fraasin pelin tietokantaan myöhemmin pelatavaksi
3. Pelata peliä

#### Pelaajatilastot

Ei toteutettu vielä, mutta tarkoituksena näyttää kuka peliä pelanneista pelaajista johtaa pankissa olevan rahasumman perusteella.

#### Uuden fraasin lisääminen

Tässä näkymässä käyttäjä voi kirjoittaa tekstikenttään haluamansa fraasin ja valita sille kategorian. Ohjeita minimipituudesta ja yhteen kategoriaan kuulumisesta
tulee noudattaa. Painettaessa Tallenna-nappia fraasi tallentuu tietokantaan ja se tulee arvuuteltavaksi tulevaisuuden pelissä.

#### Pelinäkymä

Kun käyttäjä aloittaa pelin, ohjelma siirtyy ensin näkymään, jossa käyttäjä syöttää kolmen pelaajan nimet tekstikenttiin ja painaa nappia Lisää pelaajat.
Tällöin pelaajat siirtyvät peliin aktiivisiksi pelaajiksi (ja mikäli heitä ei löytynyt jo valmiiksi tietokannasta, heidät lisätään sinne).

Itse pelinäkymässä näkyy keskellä viimeksi "pyöritetty" sektori ja sen alla arvuuteltava tehtävä ja sen kategoria.

Vuorollaan pelaajat valitsevat kolmesta mahdollisuudesta:
1. Yrittää ratkaista tehtävä eli arvata arvuuteltava fraasi
2. Pyörittää onnenpyörää
3. Ostaa vokaali (mahdollista vain pelaajalla ollessa kerättynä vähintään 250€ rahaa)

Tätäkään ei ole vielä toteutettu loppuun saakka, mutta tavoitteena on pelin jouheva kulku siihen saakka, että joku pelaajista arvaa tehtävän oikein. Tällöin voittajan keräämä rahasumma tallentuu hänen pankkiinsa ja ohjelma siirtyy takaisin aloitusnäkymään.

