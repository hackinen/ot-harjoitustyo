/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweeper.domain.*;
/**
 *
 * @author hiira
 */
public class MinesweeperGameTest {
    private MinesweeperGame mines;
    
    public MinesweeperGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mines = new MinesweeperGame(10,"test");
    }
    
    @After
    public void tearDown() {
        mines.getHighscore().getDAO().deleteTables();
    }
    
    
    @Test
    public void startGameMethodStartsTheGame() {
        mines.startGame();
        assertEquals(true,mines.getGrid().isGameOnGoing());
    }
    
    @Test
    public void stopGameMethodStopsTheGame() {
        mines.stopGame();
        assertEquals(false,mines.getGrid().isGameOnGoing());
    }
    
    @Test
    public void constructorCreatesGridAndHighscoreObjects() {
        assertTrue(mines.getGrid()!=null);
        assertTrue(mines.getHighscore()!=null);
    }
    
    @Test
    public void saveHighscoreWorks() {
        MinesweeperGame mines2 = new MinesweeperGame(10,"test2.db");
        mines2.startGame();
        mines2.stopGame();
        mines2.saveHighscore(10, "name");
        
        Highscore hs = mines2.getHighscore();
        double time = hs.getGameTime();
        
        String gametime = hs.getDAO().convertToMinutesAndSeconds(time);
        String str = hs.getTop10(10);
        
        assertEquals("10x10: \n1 --- " + gametime + " --- name\n\n",str);
        
        mines2.getHighscore().getDAO().deleteTables();
    }
    
    @Test
    public void getTop10ReturnsCorrectString() {
        mines.startGame();
        mines.stopGame();
        mines.saveHighscore(10, "Iira");
        
        double time = mines.getHighscore().getGameTime();
        String strTime = mines.getHighscore().getDAO().convertToMinutesAndSeconds(time);
        
        assertEquals("10x10: \n1 --- " + strTime + " --- Iira\n\n",mines.getTop10(10));
    }
    
    @Test
    public void methodRevealRevealsTheGrid() {
        mines.startGame();
        mines.reveal();
        for (int i = 0; i < mines.getGrid().getGridSize(); i++) {
            for (int j = 0; j < mines.getGrid().getGridSize(); j++) {
                Cell cell = mines.getGrid().getGrid()[i][j];

                if (cell.isFlagged() && !cell.isMine()) {
                    assertEquals(true,cell.isFlaggedWrong());
                    assertEquals(true,cell.isOpened());
                }

                if (!cell.isFlagged() && cell.isMine()) {
                    assertEquals(true,cell.isOpened());
                }

            }
        }
    }
    
    @Test
    public void methodSetAngryMineWorks() {
        mines.startGame();
        mines.setAngryMine(0, 0);
        
        assertTrue(mines.getGrid().getCell(0, 0).isAngryMine());
    }
    
    @Test
    public void methodFlagCellWorks() {
        mines.startGame();
        mines.flagCell(0, 0);
        
        assertTrue(mines.getGrid().getCell(0, 0).isFlagged());
    }

    @Test
    public void methodOpenCellWorks() {
        mines.startGame();
        mines.openCell(0, 0);
        
        assertTrue(mines.getGrid().getCell(0, 0).isOpened());
    }
    
    @Test
    public void methodGetValueOfCellWorks() {
        mines.startGame();
        
        assertEquals(mines.getGrid().getCell(0, 0).getValue(),mines.getValueOfCell(0, 0));
    }
    
    @Test
    public void methodCheckIfWonWorks() {
        assertTrue(!mines.checkIfWon());
    }
    
    @Test
    public void checkIfWonReturnsTrueWhenAllNumberedCellsAreOpened() {
        Grid g = mines.getGrid();
        
        for (int i = 0; i < g.getGridSize() ; i++) {
            for (int j = 0; j < g.getGridSize() ; j++) {
                if (g.getCellValue(i, j) < 9 && g.getCellValue(i, j) > 0) {
                    g.openCell(i, j);
                }
            }
        }
        
        assertTrue(mines.checkIfWon());
    }
    
    @Test
    public void getNumberOfFlagsReturnsZeroWhenNoFlags() {
        assertEquals(0,mines.getNumberOfFlags());
    }
    
    @Test
    public void getNumberOfFlagsReturnsTheCorrectNumberOfFlags() {
        mines.getGrid().getCell(0, 0).flag();
        
        assertEquals(1,mines.getNumberOfFlags());
    }
    
    @Test
    public void getNumberOfMinesReturnsTheCorrectNumberOfMines() {
        int size = mines.getGrid().getGridSize();
        assertEquals((size*size)/8, mines.getNumberOfMines());
    }
    
    @Test
    public void gameIsNotOnGoingBeforeStartingTheGame() {
        assertTrue(!mines.isGameOnGoing());
    }
    
    @Test
    public void gameIsOnGoingWhenTheGameHasStarted() {
        mines.startGame();
        assertTrue(mines.isGameOnGoing());
    }
    
    @Test
    public void methodCellIsOpenedWorksTest1() {
        mines.getGrid().openCell(0, 0);
        assertTrue(mines.cellIsOpened(0, 0));
    }
    
    @Test
    public void methodCellIsOpenedWorksTest2() {
        assertFalse(mines.cellIsOpened(0, 0));
    }
    
    @Test
    public void methodCellIsFlaggedReturnsTrueWhenCellIsFlagged() {
        mines.getGrid().getCell(0, 0).flag();
        assertTrue(mines.cellIsFlagged(0, 0));
    }
    
    @Test
    public void methodCellIsFlaggedReturnsFalseWhenCellIsNotFlagged() {
        assertTrue(!mines.cellIsFlagged(0, 0));
    }
    
    @Test
    public void methodCellIsAngryMineReturnsTrueWhenCellIsSetAsAnAngryMine() {
        mines.getGrid().getCell(0, 0).setAsAngryMine();
        assertTrue(mines.cellIsAngryMine(0, 0));
    }
    
    @Test
    public void methodCellIsAngryMineReturnsFalseWhenCellIsNotSetAsAnAngryMine() {
        assertTrue(!mines.cellIsAngryMine(0, 0));
    }
    
    @Test
    public void methodCellIsFlaggedWrongReturnsFalseWhenAMineIsFlagged() {
        Cell cell;
        int x=0;
        int y=0;
        
        for (int i=0; i<mines.getGrid().getGridSize(); i++) {
            for (int j=0; j<mines.getGrid().getGridSize(); j++) {
                if (mines.getGrid().getCellValue(i, j) == 9) {
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        mines.flagCell(x, y);
        mines.reveal();
        assertTrue(!mines.cellIsFlaggedWrong(x, y));

    }
    
    @Test
    public void methodCellIsFlaggedWrongReturnsTrueWhenANumberIsFlagged() {
        Cell cell;
        int x=0;
        int y=0;
        
        for (int i=0; i<mines.getGrid().getGridSize(); i++) {
            for (int j=0; j<mines.getGrid().getGridSize(); j++) {
                if (mines.getGrid().getCellValue(i, j) != 9 && mines.getGrid().getCellValue(i, j) != 0) {
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        mines.flagCell(x, y);
        mines.reveal();
        assertTrue(mines.cellIsFlaggedWrong(x, y));

    }
}
