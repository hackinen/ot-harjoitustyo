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
    private int arvo;
    
    public Cell(int arvo) {
        this.arvo=arvo;
    }

    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    
}
