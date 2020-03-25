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
public class Game {
    private Grid grid;
    
    public Game(int gridSize) {
        this.grid=new Grid(gridSize);
    }
}
