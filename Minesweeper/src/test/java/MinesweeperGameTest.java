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
}
