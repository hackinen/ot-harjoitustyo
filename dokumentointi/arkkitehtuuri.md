# Arkkitehtuurikuvaus

## Rakenne

Minesweeper-ohjelman rakenne koostuu käyttöjliittymää ja sovelluslogiikkaa käsittelevistä pakkauksista. Pakkausrakenne on esitetty alla:

<img src="https://github.com/hackinen/ot-harjoitustyo/blob/master/dokumentointi/misc/pakkauskaavio.png" width="750">

Pakkaus minesweeper.ui sisältää JavaFX:llä toteutetun käyttöliittymän sekä pelin grafiikat ja minesweeper.domain sisältää pelin sovelluslogiikan sekä parhaiden tulosten pysyväistallennuksesta vastaavan koodin.

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
