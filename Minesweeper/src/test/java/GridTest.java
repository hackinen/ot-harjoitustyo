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
}
