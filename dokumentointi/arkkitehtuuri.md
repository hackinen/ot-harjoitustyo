# Arkkitehtuurikuvaus

## Rakenne

Minesweeper-ohjelman rakenne koostuu käyttöjliittymää ja sovelluslogiikkaa käsittelevistä pakkauksista. Pakkausrakenne on esitetty alla:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/pakkauskaavio.png" width="750">

Pakkaus minesweeper.ui sisältää JavaFX:llä toteutetun käyttöliittymän sekä pelin grafiikat ja minesweeper.domain sisältää pelin sovelluslogiikan sekä parhaiden tulosten pysyväistallennukseen liittyvät luokat.

## Käyttöliittymä

Käyttöliittymässä on kaksi erilaista ikkunaa, joista ensimmäisessä (pelin käynnistyksessä aukeavassa) on kolme erilaista näkymää:

* aloitusvalikko (main menu)
* pelinäkymä
* parhaiden tulosten lista (highscores)

Toinen ikkuna aukeaa ensimmäisen ikkunan päälle, mikäli käyttäjä voittaa pelin. Tässä ikkunassa on yksi näkymä:

* nimimerkin syöttö pelituloksen tallennusta varten

Kun käyttäjä on syöttänyt nimimerkkinsä, sulkeutuu tämä ikkuna ja palataan aiemman ikkunan näkymään "Highscores".

Jokaista näkymää vastaa oma Scene-olionsa ja yllä mainittuja kahta eri ikkunaa omat Stage-olionsa. Käyttöliittymä on rakennettu JavaFX:llä luokkaan minesweeper.ui.MinesUi.

Käyttöliittymä on parhain mahdollisin keinoin pyritty eriyttämään sovelluslogiikasta. Ainoa sovelluslogiikan luokka, jonka käyttöliittymäluokka tuntee ja jonka metodeja se kutsuu on MinesweeperGame, joka vastaa pelin toiminnallisuudesta.

## Sovelluslogiikka

Sovelluslogiikan muodostavat luokat MinesweeperGame, Grid, Cell, Highscore ja HighscoreDAO. Luokkakaavio sovelluslogiikasta on esitetty alla:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/luokkakaavio.png" width="750">

Pelin toiminnallisuudesta vastaa luokka MinesweeperGame, joka suorittaa pelin toiminnot oliomuuttujiensa grid ja highscore avulla. Jokahetkisen pelitilanteen tallentamisesta huolehtivat luokat Grid ja Cell: Grid muodostuu useista Cell-olioista. Luokat Highscore ja HighscoreDAO vastaavat taas parhaiden tulosten seurannasta ja ajastamisesta (Highscore) sekä tietojen tallentamisesta tietokantaan (HighscoreDAO).


## Tietojen pysyväistallennus

Pysyväistallennuksen toteutuksesta vastaavat luokat Highscore ja erityisesti HighscoreDAO. Luokka HighscoreDAO suorittaa kaikki tietokantaan kohdistuvat operaatiot ja Highscore suorittaa talletettavan tiedon hankkimisen (mm. ajan mittaus). HighscoreDAO tallentaa pelin parhaat tulokset tietokantaan nimeltä:

`higscore.db`

Lisäksi testejä suorittaessa luodaan omat tietokantansa (tästä lisää [testaukseen](https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md) liittyvässä dokumentissa).

Tietokanta sisältää yhden taulun *Highscores*, johon tallennetaan tieto parametreista:
* *gridsize* - tieto pelatun kentän koosta (10x10, 16x16, 20x20 tai 30x30)
* *name* - pelaajan nimimerkki
* *time* - pelin pelaamiseen kulunut aika

## Toiminnallisuudesta

### Voitetun pelin tallentaminen

Kun käyttäjä on voittanut pelin, syöttänyt nimimerkkinsä ja painaa "save"-nappia (ohjelmakoodissa nimellä saveButton), etenee sovelluksen kontrolli seuraavalla tavalla:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/sekvenssikaavio.jpg" width="750">

Yllä olevassa sekvenssikaaviossa on siis esitelty tilanne, jossa käyttäjä *Iira* pelaa 10x10-kokoisen pelikentän ajassa 60 sekunttia ja aiempi tietokanta on tyhjä.


## Ohjelmaan jääneitä heikkouksia

### luoka MinesUi

Käyttöliittymäluokkaa MinesUi olisi voinut pilkkoa vielä pienempiin ja selkeämpiin metodeihin, sillä nyt muun muassa start-metodi on turhan pitkä ja sisältää kaikkien näkymien määrittelyt. Lisäksi grafiikkoja olisi voinut yrittää eriyttää ehkä omaan luokkaansa pakkauksen minesweeper.ui alla, jotta käyttöliittymäluokka olisi selkeämpi.

### käyttöliittymän ulkoasu

Tarkoituksena oli luoda käyttöliittymästä hieman miellyttävämmän näköinen, kun mitä se nyt on, erityisesti koskien "Highscores"-näkymää. Kuitenkin harjoitustyön arvostelun pääpaino on sovelluslogiikassa, joten parantelujen toteuttamiselle ei jäänyt tarpeeksi aikaa.
