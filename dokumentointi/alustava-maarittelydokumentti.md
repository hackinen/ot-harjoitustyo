# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tarkoituksena on toteuttaa Javalla miinaharava-peli. Pelissä on jokin n*m
kokoinen ruudukkokenttä (alkuun luodaan valikko, jossa valitaan pelattavan
kentän koko), joka sisältää miinoja ja numeroituja arvoja, jotka kertovat
kuinka moneen miinaan ympärillään kyseinen ruutu koskee. Pelin tarkoituksena
on saada pääteltyä kaikki ruudukon miinojen paikat ja merkitä ne lipulla.


## Käyttäjät

Koska kyseessä on yksilöpeli, on pelillä vain yksi käyttäjä eli pelin pelaaja.


## Käyttöliittymä

Sovelluksen käyttöliittymä koostuu kolmesta eri näkymästä: valikosta, itse pelinäkymästä ja "Highscores"-näkymästä. Sovellus siis aukeaa ensin valikkonäkymään, josta valitaan pelin haastavuus (kuinka iso ruudukko halutaan), jonka jälkeen siirrytään valittuun pelinäkymään. "Highscores"-näkymään siirrytään joko suoraan alkuvalikosta tai pelin voitettua tallentamalla oman tuloksensa tietokantaan, jonka jälkeen näkymä vaihtuu automaattisesti.

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/kayttoliittyma-luonnos.png" width="750">


## Sovelluksen tarjoamat toiminallisuudet

Ohjelma aukeaa aloitusvalikkoon, josta vaikeusasteen valinnan jälkeen siirrytään pelinäkymään, jossa käyttäjän on mahdollista pelata peliä ja aloittaa peli alusta halutessaan. Pelitilanteessa on näkyvissä miinojen ja tähän mennessä merkittyjen lippujen lukumäärä. Käyttäjä voi myös palata kesken pelin/pelin päätyttyä valikkoon. Peliin on toteutettu myös parhaiden pelitulosten seuranta (yhden pelierän kesto mitataan ajastimella pelitilanteen aloituksesta lähtien) ja näitä tuloksia voi tarkastella "Highscores"-näkymässä.


## Jatkokehitysideoita

Ohjelmaa voitaisiin jatkossa vielä laajentaa esimerkiksi seuraavilla toiminnoilla:

* ajastimen laittaminen tauolle
* peli kysyisi tallennettavaa nimimerkkiä vain, jos tulos on tulossa näkyviin parhaiden kymmenen tuloksen joukkoon
* costom-kokoinen kenttä
