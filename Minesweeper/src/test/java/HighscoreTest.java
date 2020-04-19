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
    public void methodsSaveHighscoreAndGetTop10BothWork() {
        dao.deleteTables();
        dao.createTables();
        
        hs.startTiming();
        hs.stopTiming();
        
        hs.saveHighscore(10,"Titus");
       
        double time = hs.getGameTime();
        
        int minutes = (int) time / 60;
        long seconds = Math.round(time % 60);
        String timeInMinsAndSecs = String.valueOf(minutes) + " m " + String.valueOf(seconds) + " sec";
        
        String[] pieces = hs.getTop10(10).split("\n");
        String score = pieces[1];
        
        assertEquals("1 --- " + timeInMinsAndSecs + " --- Titus",score); 
    }
    
    @Test
    public void methodGetTop10ShowsNoHighscoresWhenEmpty() {
        dao.deleteTables();
        dao.createTables();
        
        assertEquals("10x10: \n\n",hs.getTop10(10)); 
    }
}
