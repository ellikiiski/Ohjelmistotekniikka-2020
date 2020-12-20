## Käyttöohje - Onnenpyörä

#### Lataa ohjelma [täältä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/releases/tag/Loppupalautus)

### Konfigurointi

Kumpikin ohjelman versio olettaa, että käynnistyskansiossa on tiedosto<em>PhraseDB.txt</em>, jossa tulee olla vähintään
yksi validi "tietokantarivi".

### Ohjelman käynnistäminen

Käynnistäminen onnistuu komentoriviltä komennolla

<pre>
$ java -jar wheelof4tune.jar
</pre>

#### Aloitusnäkymä

Aloitusnäkymässä käyttäjällä on kolme vaihtoehtoa:
1. Pelata peliä
2. Lisätä uuden fraasin pelin tietokantaan myöhemmin pelatavaksi
3. Tarkastella pelaajastatistiikkoja

![aloitusnäkymä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/ohje1.JPG)

#### Pelinäkymä

##### Pelaajien lisäys

Kun käyttäjä aloittaa pelin, ohjelma siirtyy ensin näkymään, jossa käyttäjä syöttää kolmen pelaajan nimet tekstikenttiin ja painaa nappia Lisää pelaajat.
Tällöin pelaajat siirtyvät peliin aktiivisiksi pelaajiksi (ja mikäli heitä ei löytynyt jo valmiiksi tietokannasta, heidät lisätään sinne).

![aloitusnäkymä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/ohje3.JPG)

##### Pelaaminen

Itse pelinäkymässä näkyy ylhäällä pelaajat, keskellä arvuuteltava fraasi ja viimeksi onnenpyörästä pyöräytetty sektori, ja alhaalla viimeisin pelitapahtuma, vuorossa oleva pelaaja sekä hänen peliliikemahollisuutensa kussakin tilanteessa.

![aloitusnäkymä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/ohje4.JPG)

Vuorollaan pelaajat valitsevat kolmesta mahdollisuudesta:
1. Yrittää ratkaista tehtävä eli arvata arvuuteltava fraasi (nappi "Arvaa ratkaisua")
2. Pyörittää onnenpyörää (nappi "Pyöritä")
3. Ostaa vokaali, mikäli pelaajalla on kerättynä vähintään 250€ rahaa (nappi "Osta vokaali")

Ohjetekstit ja nappien disabloituminen ohjaavat vuorossa olevaa pelaaja ja kertovat mitä milloinkin on mahdollista tehdä.

##### Pelin loppuminen voittoon

Kun joku pelaajista arvaa fraasin oikein, peli loppuu ja siirtyy voittonäkymään. Tästä näkymästä voi joko siirtyä tarkastelemaan statiikoita tai takaisin aloitussivulle.

![aloitusnäkymä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/ohje5.JPG)

#### Pelaajatilastot

Tilastonäkymässä käyttäjä voi tarkastella pelaajien keräämiä rahasummia.

![aloitusnäkymä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/ohje6.JPG)

#### Uuden fraasin lisääminen

Fraasinäkymässä käyttäjä voi kirjoittaa tekstikenttään haluamansa fraasin ja valita sille kategorian. Ohjeita minimipituudesta ja yhteen kategoriaan kuulumisesta
tulee noudattaa. Painettaessa Tallenna-nappia fraasi tallentuu tietokantaan ja se tulee arvuuteltavaksi tulevaisuuden pelissä.

![aloitusnäkymä](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/ohje2.JPG)


