# Miinaharava
Miinaharava-pelissä on ruudukkokenttä, joka sisältää miinoja ja numeroituja arvoja, joista numeroidut arvot kertovat kuinka moneen miinaan ympärillään kyseinen ruutu koskee. Pelin tarkoituksena on saada pääteltyä kaikki ruudukon miinojen paikat numeroarvojen avulla räjäyttämättä yhtäkään miinaa (eli avaamatta miinan sisältävää ruutua).

## Tämänhetkinen eteneminen

Tällä hetkellä sovellukseen on toteutettu pelin toiminnallisuus, valikko, josta voi valita haluamansa vaikeustason sekä parhaiden tulosten tallentaminen kentän koon mukaan ja niiden katseleminen Highscores-näkymässä. Pelitoiminnallisuus toimii kaikissa kentissä, "new game"-napista voi aloittaa uuden (vastaavan kokoisen) pelin ja mikäli pelatessa avaa ruudun, jossa oli miina, loppuu peli. Jos pelin voittaa, avautuu ikkunna, johon voi syöttää nimimerkkinsä ja sovellus tallentaa pelituloksen tietokantaan.

## Dokumentointi

[Alustava määrittelydokumentti](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/alustava-maarittelydokumentti.md)

[Arkkitehtuurikuvaus](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)


[Työaikakirjanpito](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Releaset

## Komentorivitoiminnot

### Suoritettavan jarin generointi

Komento

`mvn package`

generoi hakemistoon *target* suoritettavan jar-tiedoston *Minesweeper-1.0-SNAPSHOT.jar*, joka voidaan suorittaa komennolla

`java -jar Minesweeper-1.0-SNAPSHOT.jar`


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
