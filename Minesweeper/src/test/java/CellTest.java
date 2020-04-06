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
    private Cell cell;
    
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
        cell = new Cell(0,0);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void newCellIsNotFlagged() {
        assertEquals(false,cell.isFlagged());
    }
    
    @Test
    public void newCellIsNotOpened() {
        assertEquals(false,cell.isOpened());
    }
    
    @Test
    public void flagMethodWorksBothWays() {
        cell.flag();
        assertEquals(true,cell.isFlagged());
        cell.flag();
        assertEquals(false,cell.isFlagged());
    }
    
    @Test
    public void methodOpenWorks() {
        cell.open();
        assertEquals(true,cell.isOpened());
    }
    
    @Test
    public void methodSetMineWorks() {
        cell.setMine();
        assertEquals(9,cell.getValue());
    }
    
    @Test
    public void methodSetAsAngryMineWorks() {
        cell.setAsAngryMine();
        assertEquals(true,cell.isAngryMine());
    }
    
    @Test
    public void methodSetFlaggedWrongWorks() {
        cell.setFlaggedWrong();
        assertEquals(true,cell.isFlaggedWrong());
    }
    
    @Test
    public void methodSetValueWorksWhenTheValueIsAllowed() {
        cell.setValue(3);
        assertEquals(3,cell.getValue());
    }
    
    @Test
    public void methodSetValueDoesNothingWhenValueIsNotAllowed() {
        cell.setValue(10);
        assertEquals(0,cell.getValue());
    }
}
