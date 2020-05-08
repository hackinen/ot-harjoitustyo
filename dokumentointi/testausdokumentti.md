# Testausdokumentti

Ohjelmaa on testattu automaattitestein JUnitilla sekä manuaalisesti järjestelmätason testeillä.


## Automaattitestaus

Automatisoidut testit kohdistuvat pakkauksen *minesweeper.domain* sisältämiin kaikkiin luokkiin eli käyttöliittymä on jätetty testauksesta pois. Testiluokkia ovat MinesweeperGameTest, HighscoreTest, GridTest ja CellTest.

Testaus on tehty pääosin integraatiotestein, sillä luokat liittyvät vahvasti toisiinsa. Joitakin toimintoja on testattu myös yksikkötestein, kuten muun muassa kaikki Cell-luokan toiminnot. Testeissä pyritään simuloimaan mahdollisia vastaan tulevia tilanteita pelin aikana.

Erityisesti kaikki tietokantatoiminnot on testattu luokissa HighscoreTest ja MinesweeperTest integraatiotestein. Tietokantatimintojen testaamiseen käytetään omia testiluokkia *test.db* ja *test2.db*, jotka luodaan ja poistetaan testien suorittamisen yhteydessä.


### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 96% ja haarautumakattavuus 94%. Testauksessa ei ole mukana siis käyttöliittymästä vastaavia luokkia (toisin sanoen pakausta minesweeper.ui).

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/testikattavuus.png" width="750">

Testaamatta jäi lähinnä HighscoreDAO-luokan mahdolliset virheviestit.


## Järjestelmätestaus

Järjestelmätestaus on suoritettu manuaalisesti suorittamalla ohjelmaa kahdella eri linux-ympäristössä toimivalla fuksiläppärillä. Ohjelma on ladattu ja suoritettu [käyttöohjeen](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md) mukaisesti.

Testausta suorittaessa on käyty läpi kaikki [määrittelydokumentissa](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/alustava-maarittelydokumentti.md) "Sovelluksen tarjoamat toiminnallisuudet" -osiossa mainitut toiminnot ja yritetty myös tarkastella mahdollisia virheellisiä tai ei-toivottuja syötteitä ja painalluksia.
