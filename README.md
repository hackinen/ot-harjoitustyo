# Miinaharava
Miinaharava-pelissä on ruudukkokenttä, joka sisältää miinoja ja numeroituja arvoja, joista numeroidut arvot kertovat kuinka moneen miinaan ympärillään kyseinen ruutu koskee. Pelin tarkoituksena on saada pääteltyä kaikki ruudukon miinojen paikat numeroarvojen avulla räjäyttämättä yhtäkään miinaa (eli avaamatta miinan sisältävää ruutua).

## Tämänhetkinen eteneminen

Tällä hetkellä sovellukseen on toteutettu pelin perustoiminnallisuus, alkeellinen valikko sekä parhaiden tulosten tallentaminen sekä niiden katseleminen Highscores-näkymässä. Peliä voi pelata, "new game"-napista voi aloittaa uuden pelin ja mikäli pelatessa avaa ruudun, jossa oli miina, loppuu peli. Jos pelin voittaa, avautuu ikkunna, johon voi syöttää nimimerkkinsä ja sovellus tallentaa pelituloksen tietokantaan. Tällä hetkellä peli kysyy nimimerkkiä voitettaessa, vaikka ei pääsisikään top 10 -listalle, mikä on tarkoitus muuttaa seuraavilla viikoilla. 

## Dokumentointi

[Alustava määrittelydokumentti](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/alustava-maarittelydokumentti.md)

[Arkkitehtuurikuvaus](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)


[Työaikakirjanpito](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Komentorivitoiminnot


### Pelin käynnistäminen komentoriviltä

Minesweeper-pelin voi käynnistää komentoriviltä Minesweeper-projektikansiosta komennolla:

`mvn compile exec:java -Dexec.mainClass=minesweeper.ui.Main`


### Testaus

Testit suoritetaan komentorivillä komennolla:

`mvn test`

Testikattavuusraportti luodaan komennolla:

`mvn jacoco:report`

Kattavuusraporttia voi tarkastella avaamalla tiedoston *target/site/jacoco/index.html* selaimella.


### Checkstyle

Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla:

`mvn jxr:jxr checkstyle:checkstyle`

Komennon suoritettua mahdolliset virheilmoitukset voi tarkastaa avaamalla tiedoston *targer/site/checkstyle.html* jollakin selaimella.
