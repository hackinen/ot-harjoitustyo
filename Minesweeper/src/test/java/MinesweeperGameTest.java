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
        mines = new MinesweeperGame(10);
    }
    
    @After
    public void tearDown() {
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


}
