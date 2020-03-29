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
    
    public Grid(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        
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
    
    
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
    
    public int getCellValue(int x, int y) {
        return grid[x][y].getValue();
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
