# Miinaharava
Miinaharava-pelissä on ruudukkokenttä, joka sisältää miinoja ja numeroituja arvoja, joista numeroidut arvot kertovat kuinka moneen miinaan ympärillään kyseinen ruutu koskee. Pelin tarkoituksena on saada pääteltyä kaikki ruudukon miinojen paikat numeroarvojen avulla räjäyttämättä yhtäkään miinaa (eli avaamatta miinan sisältävää ruutua).

Tällä hetkellä sovellukseen on toteutettu pelin perustoiminnallisuus, pelin voittamisen tarkistamista lukuunottamatta. Peliä voi pelata, "new game"-napista voi aloittaa uuden pelin ja mikäli pelatessa avaa ruudun, jossa oli miina, loppuu peli.

## Dokumentointi

[Alustava määrittelydokumentti](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/alustava-maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komentorivillä komennolla:

`mvn test`

Testikattavuusraportti luodaan komennolla:

`mvn jacoco:report`

Kattavuusraporttia voi tarkastella avaamalla tiedoston *target/site/jacoco/index.html* selaimella.


### Pelin käynnistäminen komentoriviltä

Minesweeper-pelin voi käynnistää komentoriviltä komennolla:

`mvn compile exec:java -Dexec.mainClass=minesweeper.ui.Main`
