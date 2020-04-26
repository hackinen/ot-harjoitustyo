/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

/**
 *
 * @author hiira
 */
public class Cell {
    /*
    variable value:
    1-8 = numbers from 1 to 8
    0 = empty cell
    9 = mine
    */
    private int value;
    private boolean opened = false;
    private boolean flagged = false;
    private boolean flaggedWrong = false;
    private boolean isAngryMine = false;
    
    public static int MINE = 9;
    public static int EMPTY = 0;
    
    public Cell() {
    }
    
    /**
     * Marks the unflagged cell with a flag or removes the existing flag
     */
    public void flag() {
        this.flagged = !flagged;
    }
    
    /**
     * Sets the cell as opened
     */
    public void open() {
        this.opened = true;
    }
    
 
    public boolean isMine() {
        return value == MINE;
    }
    
    public boolean isAngryMine() {
        return isAngryMine;
    }
    
    public boolean isFlagged() {
        return flagged;
    }

    public boolean isOpened() {
        return this.opened;
    }
    
    public boolean isFlaggedWrong() {
        return this.flaggedWrong;
    }
    
    public int getValue() {
        return value;
    }
     
    public void setMine() {
        this.value = MINE;
    }
   
    public void setFlaggedWrong() {
        this.flaggedWrong = true;
    }

    public void setValue(int value) {
        if (value < EMPTY || value > MINE) {
            return;
        }
        this.value = value;
    }
    
    public void setAsAngryMine() {
        this.isAngryMine = true;
    }
    
    
}
