/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class HighscoreTest {
    private Highscore hs;
    private HighscoreDAO dao;
    
    public HighscoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new HighscoreDAO("test.db");
        hs = new Highscore("test.db");
    }
    
    @After
    public void tearDown() {
        dao.deleteTables();
    }

   
    
    @Test
    public void getGameTimeMethodWorks() {
        hs.setStartTime(1000000000);
        hs.setEndTime(2000000000);
        
        double time =1.0;
        assertEquals(time,hs.getGameTime(),1.0/1e6);
    }
    
    @Test
    public void methodSaveHighscoreWorks() {
        hs.startTiming();
        hs.stopTiming();
        
        hs.saveHighscore("Titus");
        ArrayList<Double> list = dao.getHighscoresByName("Titus");
        
        Double time = hs.getGameTime();
        
        assertEquals(time,list.get(0));
        
    }
}
