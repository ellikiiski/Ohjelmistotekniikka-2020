## Testausdokumentti

Ohjelmaa on testattu JUnit testein sekä manuaalisesti graafista käyttöliittymää käyttäen. Myös järjestelmätestaus on suoritettu Windows-koneella.

### JUnit-testaus

Testauksen kehittyessä pitkin kurssia, testikansiossa oli ajan mittaan varsin monta testitiedostoa, jotka testasivat eri luokkia. Kuitenkin loppupalautukseen testausta suunnitellessani
huomasin, etteivät erilliset testiluokat testanneet mitään sellaista, jota <em>GameTest</em> ei jo testaisi. Niinpä loppupalautuksesta on poistettu kokonaan kaikki muut paitsi
kyseinen testiluokka, koska se testaa varsin kattavasti kaikkia ohjelman ominaisuuksia.

#### Sovelluslogiikka

Sovelluslogiikkaa testataan enimmäksiin peliliikkeisiin, kuten konsonantin arvaamiseen, liittyvin testein. Metodeille annetaan sekä oikeita, että vääriä parametreja, jotta
saadaan testattua sekä toimivuus, että virheen käsittely.

Osa sovelluslogiikan metodeista (esim. <em>declareWinner()</em>) kutsuvat myös <em>dao</em>-paketin luokkia suorituksessaan, jolloin riippuvuudet tulee testattua suurimmilta osin
yksikkötestauksen lomassa.

#### Dao

Kuten yllä mainittu, erillinen tietokantatestaus ei tuottanut parannusta testikattavuuteen, joten sellaista ei ole jätetty projektiin turhaan roikkumaankaan. Varsin hyvä
kattavuus myös tallennuksen testaamiselle saavutettuu <em>GameTest</em>-luokan testeillä.

### Testikattavuus

Kun jätetään käyttöliittymä pois laskuista, sovelluksen testikattavuus lähentelee 90 prosenttia sekä rivi, että haarautumakattavuudessa.

![jacoco](https://github.com/ellikiiski/Ohjelmistotekniikka-2020/blob/main/kuvat/testiraportti.JPG)

Tietyt virhetilanteet ja triviaalit getterit ja merkkijonojen muokkaukset jäivät testaamatta automaattisin testein.

### Järjestelmätestaus

Sovelluksen lataamista ja suorittamista on testattu valitettavasti vain yhdellä tietokoneella. Ohjelman suorittaminen on onnistunut moitteetta käyttöohjeen
opastamalla tavalla niin konfiguraatioiden kuin määrittelydokumentissa määriteltyjen toiminnallisuuksienkin osalta.

### Selvittämättä jääneet laatuongelmat

Mikäli suorituskansiosta puuttuu tarvittavat tietokantatiedostot, ohjelma ei osaa ilmoittaa virheestä käyttäjälle järkevällä tavalla.
