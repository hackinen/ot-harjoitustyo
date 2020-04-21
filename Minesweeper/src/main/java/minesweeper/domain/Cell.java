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
    private boolean opened;
    private boolean flagged;
    private boolean flaggedWrong;
    private boolean isAngryMine;
    private int x, y;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.opened = false;
        this.flagged = false;
        this.isAngryMine = false;
        this.flaggedWrong = false;
    }
    
    public void flag() {
        this.flagged = !flagged;
    }
    
    public void open() {
        this.opened = true;
    }
    
    public void setMine() {
        this.value = 9;
    }
    
    public boolean isMine() {
        if (value == 9) {
            return true;
        }
        return false;
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
    
    public void setFlaggedWrong() {
        this.flaggedWrong = true;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0 || value > 9) {
            return;
        }
        this.value = value;
    }
    
    public void setAsAngryMine() {
        this.isAngryMine = true;
    }
    
    
}
