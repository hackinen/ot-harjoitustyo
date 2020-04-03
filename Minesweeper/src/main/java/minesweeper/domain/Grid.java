/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;
import minesweeper.ui.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
/**
 *
 * @author hiira
 */
public class Grid {
    private int size;       //the size of the grid (square)
    private Cell[][] grid;
    private boolean gameOnGoing;
    
    public Grid(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        this.gameOnGoing=true;
        
        for (int i=0 ; i<size ; i++) {
            for (int j=0; j<size ; j++) {
                grid[i][j]=new Cell(i,j);
            }
        }
        
        addMinesToGrid((size*size)/8);
        addValuesToGrid();
    }
    
    //constructor for testing the methods addMinesToGrid and addValuesToGrid
    public Grid() {
        this.size=10;
        this.grid=new Cell[size][size];
        
        for (int i=0 ; i<size ; i++) {
            for (int j=0; j<size ; j++) {
                grid[i][j]=new Cell(i,j);
            }
        }
    }
    
    public void addMinesToGrid(int mines) {
        Random random = new Random();
        
        HashSet<ArrayList<Integer>> mineLocations = new HashSet<>(); 
        
        while (true) {
            if (mineLocations.size()==mines) {
                break;
            }
            ArrayList list = new ArrayList<>();
            list.add(random.nextInt(this.size));    //x-coordinate
            list.add(random.nextInt(this.size));    //y-coordinate
            
            if (!mineLocations.contains(list)) {  
                mineLocations.add(list);
            }
        }
        
        for (ArrayList coordinates : mineLocations) {
            grid[(int) coordinates.get(0)][(int) coordinates.get(1)].setMine();
        }
    }
    
    public void addValuesToGrid() {
        for (int x=0 ; x<size ; x++) {
            for (int y=0 ; y<size ; y++) {
                if (!grid[x][y].isMine()) {
                    int mines=isMine(x-1,y-1)+isMine(x-1,y)+isMine(x-1,y+1)+
                        isMine(x,y-1)+isMine(x,y+1)+isMine(x+1,y-1)+isMine(x+1,y)
                        + isMine(x+1,y+1);
                    grid[x][y].setValue(mines);
                }
            }
        }
    }
    
    public int isMine(int x, int y) {
        if(x<0 || y<0 || x>=size || y>=size) {
            return 0;
        } else if (grid[x][y].isMine()) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void openCell(int x, int y) {
        if (getCell(x,y).isFlagged()) {
            return;
        }
        if (getCellValue(x,y)==0) {
            openUntilNotEmpty(x,y);
        }
        
        if (getCellValue(x,y)>0 ) {
            getCell(x,y).open();
            
            if (getCellValue(x,y)==9) {
                this.gameOnGoing=false;
            }
        }
    }
    
    public void openUntilNotEmpty(int x, int y) {
        if (x<0 || y<0 || x>=size || y>=size) {
            return;
        }
        if (this.getCell(x,y).isOpened()) {
            return;
        }
        if (this.getCell(x, y).isFlagged()) {
            return;
        }
        if (getCellValue(x,y)!=0 && getCellValue(x, y)<9) {
            getCell(x,y).open();
            return;
        }
        getCell(x,y).open();
        openUntilNotEmpty(x-1,y);
        openUntilNotEmpty(x-1,y+1);
        openUntilNotEmpty(x,y+1);
        openUntilNotEmpty(x+1,y+1);
        openUntilNotEmpty(x+1,y);
        openUntilNotEmpty(x+1,y-1);
        openUntilNotEmpty(x,y-1);
        openUntilNotEmpty(x-1,y-1);
    }
    
    public void revealTheGrid() {
        for (int i=0; i<this.size; i++) {
            for (int j=0; j<this.size; j++) {
                Cell cell=grid[i][j];
                
                if (cell.isFlagged() && !cell.isMine()) {
                    cell.setFlaggedWrong();
                    cell.open();
                }
                
                if (!cell.isFlagged() && cell.isMine()) {
                    cell.open();
                }
                
            }
        }
    }
    
    public void setAngryMine(int x, int y) {
        grid[x][y].setAsAngryMine();
    }
    
    public boolean checkIfWon() {
        boolean areThereUnopenedNumbers = false;
        for (int x=0; x<size; x++) {
            for (int y=0; y<size; y++) {
                if (getCellValue(x,y)<9 && getCellValue(x,y)!=0 && !getCell(x,y).isOpened()) {
                    areThereUnopenedNumbers=true;
                }
                if (areThereUnopenedNumbers) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
    
    public int getCellValue(int x, int y) {
        return grid[x][y].getValue();
    }
    
    public boolean isGameOnGoing() {
        return this.gameOnGoing;
    }
    
    public boolean cellIsOpened(int x, int y) {
        return grid[x][y].isOpened();
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (int i=0 ; i<size ; i++) {
            for (int j=0 ; j<size; j++) {
                str = str + grid[i][j].getValue() + " ";
            }
            str = str + "\n";
        }
        
        return str;
    }
}
