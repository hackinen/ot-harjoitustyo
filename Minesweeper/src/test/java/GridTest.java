/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import minesweeper.domain.Grid;
import minesweeper.domain.Cell;
/**
 *
 * @author hiira
 */
public class GridTest {
    private Random r;
    private Grid grid;
    
    public GridTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        r = new Random();
        grid = new Grid();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void gridContainsCells() {
        assertEquals(0,grid.getCell(r.nextInt(10), r.nextInt(10)).getValue());
    }
    
    @Test
    public void addMinesToGridAddsTheCorrectNumberOfMines() {
        grid.addMinesToGrid(9);
        
        int mines = 0;
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if (grid.getCellValue(i,j)==9) {
                    mines++;
                }
            }
        }
        
        assertEquals(9,mines);
    }
    
    @Test
    public void isMineMethodReturnsZeroIfCellIsNotAMine() {
        grid.addMinesToGrid(9);
        int i_test = 0;
        int j_test = 0;
        
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if (grid.getCellValue(i, j)!=9) {
                    i_test=i;
                    j_test=j;
                }
            }
        }
        assertEquals(0,grid.isMine(10, 123));
        assertEquals(0,grid.isMine(-1, -8));
        assertEquals(0,grid.isMine(i_test,j_test));
    }
    
    @Test
    public void isMineMethodReturnsOneIfCellIsAMine() {
        grid.addMinesToGrid(9);
        int i_test = 0;
        int j_test = 0;
        
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if (grid.getCellValue(i, j)==9) {
                    i_test=i;
                    j_test=j;
                }
            }
        }
        
        assertEquals(1,grid.isMine(i_test,j_test));
    }
    
    @Test
    public void addValuesToGridAddsCorrectValues() {
        grid.getCell(0, 0).setMine();
        grid.addValuesToGrid();
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 0 && j == 1) || (i == 1 && j == 1) || (i == 1 && j == 0)) {
                    assertEquals(1, grid.getCellValue(i, j));
                } else if (i == 0 && j == 0) {
                    assertEquals(9, grid.getCellValue(i, j));
                } else {
                    assertEquals(0, grid.getCellValue(i, j));
                }
            }
        }
    }
    
    @Test
    public void openUntilNotEmptyWorks() {
        grid.getCell(0, 0).setMine();
        grid.addValuesToGrid();
        grid.openUntilNotEmpty(2, 2);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 0 && j == 1) || (i == 1 && j == 1) || (i == 1 && j == 0)) {
                    assertEquals(true, grid.cellIsOpened(i, j));
                } else if (i == 0 && j == 0) {
                    assertEquals(false, grid.cellIsOpened(i, j));
                } else {
                    assertEquals(true, grid.cellIsOpened(i, j));
                }
            }
        }
    }
    
    @Test
    public void openCellWorksWhenTheCellIsEmpty() {
        grid.getCell(0, 0).setMine();
        grid.addValuesToGrid();
        grid.openCell(2, 2);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 0 && j == 1) || (i == 1 && j == 1) || (i == 1 && j == 0)) {
                    assertEquals(true, grid.cellIsOpened(i, j));
                } else if (i == 0 && j == 0) {
                    assertEquals(false, grid.cellIsOpened(i, j));
                } else {
                    assertEquals(true, grid.cellIsOpened(i, j));
                }
            }
        }
    }
    
    @Test
    public void openCellWorksWhenTheCellIsANumber() {
        grid.getCell(0, 0).setMine();
        grid.addValuesToGrid();
        grid.openCell(0, 1);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 1) {
                    assertEquals(true, grid.cellIsOpened(i, j));
                } else if ((i == 1 && j == 1) || (i == 1 && j == 0)) {
                    assertEquals(false, grid.cellIsOpened(i, j));
                } else if (i == 0 && j == 0) {
                    assertEquals(false, grid.cellIsOpened(i, j));
                } else {
                    assertEquals(false, grid.cellIsOpened(i, j));
                }
            }
        }
    }
    
    @Test
    public void openCellMethodStopsGameSessionWhenTheOpenedCellWasAMine() {
        grid.getCell(0, 0).setMine();
        grid.addValuesToGrid();
        grid.openCell(0, 0);
        
        assertEquals(false,grid.isGameOnGoing());
    }
    
    @Test
    public void openCellDoesNotOpenTheCellIfTheCellIsFlagged() {
        grid.getCell(0, 0).setMine();
        grid.addValuesToGrid();
        grid.getCell(0, 0).flag();
        grid.openCell(0, 0);
        
        assertEquals(false,grid.cellIsOpened(0, 0));
    }
    
    @Test
    public void setAngryMineMethodWorks() {
        grid.setAngryMine(0, 0);
        assertEquals(true,grid.getCell(0, 0).isAngryMine());
    }
    
    @Test
    public void methodStartGameSessionWorks() {
        grid.startGameSession();
        assertEquals(true,grid.isGameOnGoing());
    }
    
    @Test
    public void methodRevealTheGridOpensAllMines() {
        grid.revealTheGrid();
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid.getCellValue(i, j) == 9) {
                    assertEquals(true,grid.cellIsOpened(i, j));
                }
            }
        }
    }
    
    @Test
    public void methodRevealTheGridNoticesAllWronglyFlaggedCells() {
       grid.getCell(0,0).flag();
       grid.revealTheGrid();
       
       assertTrue(grid.getCell(0, 0).isFlaggedWrong());
       assertTrue(!grid.getCell(1, 0).isFlaggedWrong());
    }
    
    @Test
    public void getNumberOfMinesReturnsTheCorrectNumber() {
        Grid g = new Grid(10);
        assertEquals((10 * 10) / 8,g.getNumberOfMines());
    }
    
    @Test
    public void getNumberOfFlaggedCellsReturnsZeroWhenNoFlaggedCells() {
        assertEquals(0,grid.getNumberOfFlaggedCells());
    }
    
    @Test
    public void getNumberOfFlaggedCellsReturnsTheCorrectNumber() {
        grid.getCell(0, 0).flag();
        grid.getCell(2, 3).flag();
        
        assertEquals(2,grid.getNumberOfFlaggedCells());
    }
    
}
