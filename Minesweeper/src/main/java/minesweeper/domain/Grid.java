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
public class Grid {
    private int size;       //the size of the grid
    private int mines;      //the number of mines on the grid
    
    public Grid(int size, int mines) {
        this.size=size;
        this.mines=mines;
    }
}
