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

import minesweeper.domain.Cell;
/**
 *
 * @author hiira
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void newCellIsNotFlagged() {
        Cell cell = new Cell(0,0);
        
        assertEquals(false,cell.isFlagged());
    }
    
    @Test
    public void newCellIsNotOpened() {
        Cell cell = new Cell(0,0);
        
        assertEquals(false,cell.isOpened());
    }
    
    @Test
    public void flagMethodWorksBothWays() {
        Cell cell = new Cell(0,0);
        cell.flag();
        assertEquals(true,cell.isFlagged());
        cell.flag();
        assertEquals(false,cell.isFlagged());
    }
}