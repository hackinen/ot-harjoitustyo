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
    private boolean covered;
    private boolean flagged;
    private int x, y;
    
    public Cell(int x, int y) {
        this.x=x;
        this.y=y;
        
        this.covered=true;
        this.flagged=false;
    }
    
    public void flag() {
        this.flagged=!flagged;
    }
    
    public void setMine() {
        this.value=9;
    }
    
    public boolean isMine() {
        if (value==9) {
            return true;
        }
        return false;
    }
    
    public boolean isFlagged() {
        return flagged;
    }

    public boolean isCovered() {
        return this.covered;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value=value;
    }
    
    
}
