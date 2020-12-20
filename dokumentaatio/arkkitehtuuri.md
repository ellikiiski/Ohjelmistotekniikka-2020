## Arkkitehtuuri

### Rakenne

Ohjelmassa on kolme päätasoa ikäänkuin päällekkäin: ylhäältä alas käyttöliittymä, sovelluslogiikka, tietokannat.

![luokkakaavio](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/luokkakaavio.jpg)

Yllä olevas kuva ei vastaa yksityiskohtaisesti ohjelman luokka- ja pakettirakennetta, vaan kuvaa toiminnan ymmärtämisen kannalta merkittävät luokat. Todellisuudessa ohjelma sisältää vielä
- <em>Main</em>-luokan sisältävän <em>main</em>-paketin, josta graafinen käyttöliittymä käynnistetään
- useita pienempiä pelilogiikan osia kuvaavio luokkia <em>gamlelogic</em>-paketissa
- tietokantaan tallentamisessa avustavat <em>PlayerDBhandler</em>- ja <em>PhraseDBhandler</em>-luokat paketissa <em>dao</em>
- lukuisia graafisen käyttöliittymän rakentamisessa apuna käytettyjä näkymäluokkia pakkauksessa <em>ui.views</em> ja niiden rakentamisessa käytettyjä asetteluluokkia pakkauksessa <em>ui.layouts</em>

### Käyttöliittymä

Itse graafinen käyttöliittymä löytyy pakkauksesta <em>wheeloffortune.ui</em>, sekä siihen liittyvät apuluokat pakkauksista <em>wheeloffortune.ui.views</em> ja <em>wheeloffortune.ui.layouts</em>.

Käyttöliittymässä on aloitusnäyttö, josta pääsee kolmeen päähaaraan:
1. Pelaajastatistiikkanäkymään
2. Lisäämään uuden fraasin tietokantaan
3. Pelaamaan peliä

Jokainen näistä on toteutettu rajapinnan <em>View</em> toteuttavana näkymäluokkana, ja käyttävät hyväkseen rajapinnan <em>Layout</em> toteuttavia asetteluluokkia. Näin vältetään pahimmat spagetit käyttöliittymänkin koostavasta koodista.
Pelinäkymä on muita näkymiä huomattavasti monimutkaisempi ja koostuukin kokonaisuudessaan kolmesta eri näkymästä <em>AddPlayerView</em>, <em>GameView</em> ja <em>GameOverView</em>.

#### Pelaajastatistiikkanäkymä

Statistiikkanäkymä on varsin yksikertainen, se koostuu vain tekstikomponentista (pelaajatilasto) ja takaisin aloitussivulle vievästä napista.

#### Fraasinlisäysnäkymä

KESKEN

#### Pelinäkymä

##### Pelaajien lisäminen

Pelinäkymä aukeaa ensin näkymään, jossa lisätään pelaajat. Se koostuu kolmesta tekstikentästä, pelin aloittavasta napista sekä <em>ErrorMessageLayout</em>-asettelusta, johon mahdolliset virheilmoitukset tulevat näkyviin.

##### Pelin pelaaminen

Varsinainen pelinäkymä <em>GameView</em> puolestaan koostuu peräti kuudesta eri <em>Layout</em>-rajapinnan toteuttavasta komponentista:
- <em>PlayersLayout</em> (koostuu edelleen kolmesta <em>OnePlayerLayout</em>-oliosta) kertoo käyttäjälle pelitilanteen.
- <em>PhraseLayout</em> näyttää ratkaistavana olevan fraasin kategorian ja itse fraasin siinä muodossa kuin se on siihen mennessä saatu ratkaistua.
- <em>WheelLayout</em> yksinkertaisesti näyttää viimeksi pyöräytetyn sektorin nimen.
- <em>TurnLayout</em> muuttuu sen mukaan, kuka on vuorossa ja mitä on viimeksi tapahtunut, näyttämällä viimeisimmän tapahtuman ja vuorossa olevan pelaajan sekä tarjolla. olevat peliliikevaihtoehdot nappeina (hyödyntää <em>ButtonLayout</em>-luokkaa).
- <em>GuessLayout</em> muuttuu sen mukaan, mikä toiminto on aiemmin valittu, joko konsonantin veikkaamiseen, vokaalin ostamiseen tai tehtävän ratkaisemisen yrittämiseen soveltuvaksi. Se koostuu tekstikentästä, tilanteen mukaan vaihtuvasta ohjetekstistä ja kolmesta kuhunkin tilanteeseen tarkoitetusta napista (hyödyntää <em>ButtonLayout</em>-luokkaa).
- <em>ErrorMessageLayout</em> pysyy tyhjänä niin kauan kun käyttäjä pelaa peliä ohjeiden mukaan ja sääntöjen puitteissa. Mikäli käyttäjä yrittää antaa vääränlaisen syötteen, asetteluun ilmaantuu virhettä vastaava ilmoitus.

##### Pelin päättyminen voittoon

Kun peli loppuu

### Sovelluslogiikka

Sovelluslogiikkaan liittyvät komponentit löytyvät pakkauksesta <em>wheeloffortune.domain</em>.

Sovelluslogiikka pyörii lähinnä <em>Game</em>-luokassa ja muut luokat ovat tavallaan apuolioita pelin pyörittämisessä. Oleellisesti peli sisältää kolme <em>Player</em>-olioita, yhden <em>Wheel</em>-olion ja yhden <em>Phrase</em>-olion. Muut "pienemmät" luokat ovat edelleen edellä mainittujen "apuolioita".

### Pysyväistallennus

Pysyväistallennukseen liittyvät komponentit löytyvät pakkauksesta <em>wheeloffortune.dao</em>.

Ohjelma käyttää hyväkseen kahta tietokantaa: pelaajatietokanta (<em>PlayerDao</em>) ja fraasitietokanta (<em>PhraseDao</em>). Tiedot tallennetaan tiedostoihin <em>PlayerDB.txt</em> ja <em>PhraseDB.txt</em>, joista ne myös tarpeen tullen luetaan.

### Esimerkki toiminnallisuudesta

Alla sekvenssikaavio onnenpyörän pyörittämisestä.

![sekvenssikaavio spin](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/sekvenssikaavio.png)
