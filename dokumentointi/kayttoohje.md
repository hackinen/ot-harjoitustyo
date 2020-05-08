# Käyttöohje

## Konfigurointi

Ohjelma ei vaadi erillistä konfigurointia.

## Ohjelman käynnistäminen

Ohjelma käynnistetään ohjelman sijaintikansiosta komennolla

`java -jar minesweeper.jar`

## Aloitusvalikko ja pelin aloittaminen

Peli käynnistyy aloitusvalikkonäkymään:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/mainmenu.png" width="350">

Valikosta löytyy neljä eri kokomahdollisuutta pelattavalle miinaharavapelille: 10 x 10, 16 x 16, 20 x 20 ja 30 x 30. Lisäksi valikosta on mahdollista mennä tarkastelemaan läpi pelattujen pelien parhaita aikoja (highscores).

Peli aloitetaan valitsemalla jokin neljästä pelikentästä.

## Pelin pelaaminen

Valittuaan pelikentän aloitusvalikosta siirrytään pelinäkymään (tässä 16 x 16):

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/gamemode.png" width="350">

Peli aloitetaan painamalla hiiren vasemmalla näppäimellä mistä tahansa ruudusta ja päättelemällä tämän jälkeen miinojen paikkoja. Ruudun numeroarvo kertoo, kuinka moneen miinaan tämä kyseinen ruutu koskee (eli kuinka monta miinaa sitä ympäröivissä ruuduissa on). Kun pelaaja on päätellyt miinan paikan, voi pelaaja merkitä tämän lipulla painamalla hiiren oikeaa näppäintä. Lipun saa poistettua painamalla lipun päällä uudestaan hiiren oikeaa näppäintä. Miinojen ja laitettujen lippujen määrä näkyy pelin yläpalkissa oikealla.

Mikäli pelaaja häviää pelin, eli avaa miinan sisältävän ruudun, voi pelin aloittaa uudestaan yläpalkista löytyvästä "new game"-napista.

## Pelin voittaminen ja highscoren tallentaminen

Kun pelaaja on avannut kaikki numeroarvolliset ruudut, voittaa hän pelin. Tällöin aukeaa uusi ikkuna, johon pelaaja voi syöttää nimimerkkinsä highscoren tallentamista varten:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/savehighscore.png" width="350">

Kun pelaaja on syöttänyt nimimerkkinsä ja painaa "Save"-nappia, siirrytään automaattisesti Highscores-näkymään.

## Highscores-näkymä

Pelaaja voi tarkastella pelin parhaita tuloksia Highscores-näkymässä:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/higscores.png" width="350">

Näkymä näyttää maksimissaan kymmenen parasta tulosta jokaisesta pelikentästä.
