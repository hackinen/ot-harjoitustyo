# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tarkoituksena on toteuttaa Javalla miinaharava-peli. Pelissä on jokin n*m
kokoinen ruudukkokenttä (alkuun luodaan valikko, jossa valitaan pelattavan
kentän koko), joka sisältää miinoja ja numeroituja arvoja, jotka kertovat
kuinka moneen miinaan ympärillään kyseinen ruutu koskee. Pelin tarkoituksena
on saada pääteltyä kaikki ruudukon miinojen paikat ja merkitä ne lipulla.


## Käyttäjät

Koska kyseessä on yksilöpeli, on pelillä vain yksi käyttäjä eli pelin pelaaja.


## Käyttöliittymäluonnos

Sovelluksen käyttöliittymä koostuu mahdollisesti kahdesta eri näkymästä: valikosta ja itse pelinäkymästä. Sovellus siis aukeaa ensin valikkonäkymään, josta valitaan pelin haastavuus (kuinka iso ruudukko halutaan), jonka jälkeen siirrytään valittuun pelinäkymään. Mikäli valikkonäkymää ei ehditä toteuttamaa, koostuu käyttölittymä ainoastaan pelinäkymästä.

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/kayttoliittyma-luonnos.png" width="750">


## Perusversion tarjoama toiminallisuus

Perusversiossa ohjelma aukeaa suoraan pelinäkymään (ilman valikkoa), jossa käyttäjän on mahdollista pelata peliä ja aloittaa peli alusta halutessaan. (toteutettu)


## Jatkokehitysideoita

Perusversion jälkeen ohjelmaa tullaan ajan salliessa parantelemaan esimerkiksi seuraavilla toiminnoilla:

* valikon luominen ja ruudukon koon valinta (toteutettu)
* pelin teeman muuttaminen (ruutujen väri)
* miinojen määrän muuttaminen
* ajastin ja parhaiden tulosten tallentaminen (toteutettu)
* mahdollisesti vielä käyttäjäkirjautuminen tai nimimerkin luominen, jotta listauksessa näkyy tuloksen saanut pelaaja (toteutettu)

Kehitysideoista vähintään valikon toteuttaminen pyritään saada kurssin loppuun mennessä toteutettua.
