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
    private int size = 10;       //the size of the grid (square)
    private int mines;
    private Cell[][] grid;
    private boolean gameOnGoing;
    
    /**
     * The constructor creates the grid of cells and adds all the mines and numbers to the grid (calls methods addMinesToGrid and addValuesToGrid)
     * 
     * @param size the length of one side of the grid (the grid is a square)
     */
    public Grid(int size) {
        this.size = size;
        this.mines = (size * size) / 8;
        this.grid = new Cell[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
        
        addMinesToGrid(mines);
        addValuesToGrid();
    }
    
    /**
     * This is a constructor for testing the methods addMinesToGrid and addValuesToGrid: this constructor only creates a 10x10-grid, but does not add any mines or values to it 
    */
    public Grid() {
        this.size = 10;
        this.grid = new Cell[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    
    /**
     * This method randomly sets the mines on the grid
     * @param mines the number of mines
     */
    public void addMinesToGrid(int mines) {
        Random random = new Random();
        
        HashSet<ArrayList<Integer>> mineLocations = new HashSet<>(); 
        
        while (true) {
            if (mineLocations.size() == mines) {
                break;
            }
            ArrayList<Integer> list = new ArrayList<>();
            list.add(random.nextInt(this.size));    //x-coordinate
            list.add(random.nextInt(this.size));    //y-coordinate
            
            if (!mineLocations.contains(list)) {  
                mineLocations.add(list);
            }
        }
        
        for (ArrayList<Integer> coordinates : mineLocations) {
            grid[(int) coordinates.get(0)][(int) coordinates.get(1)].setMine();
        }
    }
    
    /**
     * This method adds the correct numbers to the grid after the mines have been added
     */
    public void addValuesToGrid() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (!grid[x][y].isMine()) {
                    int mines = isMine(x - 1, y - 1) + isMine(x - 1, y) + isMine(x - 1, y + 1) + 
                        isMine(x, y - 1) + isMine(x, y + 1) + isMine(x + 1, y - 1) + isMine(x + 1, y)
                        + isMine(x + 1, y + 1);
                    grid[x][y].setValue(mines);
                }
            }
        }
    }
    
    /**
     * This method is only used in the method addValuesToGrid when finding the mines that are surrounding a certain cell: this method checks if the cell in (x,y) is a mine or not
     * @param x
     * @param y
     * @return 1 if the cell in (x,y) is a mine, 0 otherwise
     */
    public int isMine(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) {
            return 0;
        } else if (grid[x][y].isMine()) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * Opens the cell in (x,y): however, 1) if the cell is flagged, it does not get opened, 2) if the cell is an empty cell, it calls the method openUntilNotEmpty(x,y) and 3) if the cell is a mine, the game session stops
     * @param x
     * @param y 
     */
    public void openCell(int x, int y) {
        if (getCell(x, y).isFlagged()) {
            return;
        }
        if (getCellValue(x, y) == 0) {
            openUntilNotEmpty(x, y);
        }
        
        if (getCellValue(x, y) > 0) {
            getCell(x, y).open();
            
            if (getCell(x, y).isMine()) {
                stopGameSession();
            }
        }
    }
    
    /**
     * This recursive method opens all the cells surrounding the empty cell in (x,y). It goes on until there are no more empty cells in the surrounding cells.
     * @param x
     * @param y 
     */
    public void openUntilNotEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size || this.getCell(x, y).isOpened() || this.getCell(x, y).isFlagged()) {
            return;
        }
        if (getCellValue(x, y) != 0 && getCellValue(x, y) < 9) {
            getCell(x, y).open();
            return;
        }
        getCell(x, y).open();
        openUntilNotEmpty(x - 1, y);
        openUntilNotEmpty(x - 1, y + 1);
        openUntilNotEmpty(x, y + 1);
        openUntilNotEmpty(x + 1, y + 1);
        openUntilNotEmpty(x + 1, y);
        openUntilNotEmpty(x + 1, y - 1);
        openUntilNotEmpty(x, y - 1);
        openUntilNotEmpty(x - 1, y - 1);
    }
    
    /**
     * Reveals all the unflagged mines and wrong flags in the grid (sets them as opened).
     */
    public void revealTheGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Cell cell = grid[i][j];
                
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
    
    /**
     * Sets the cell in coordinates (x,y) as an angry mine (a mine that has been opened)
     * @param x
     * @param y 
     */
    public void setAngryMine(int x, int y) {
        grid[x][y].setAsAngryMine();
    }
    
 
    public Cell[][] getGrid() {
        return this.grid;
    }
    
    public int getGridSize() {
        return this.size;
    }
    
    /**
     * Returns the cell in (x,y)
     * @param x
     * @param y
     * @return Cell
     */
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
    
    /**
     * Returns the value of the cell in (x,y)
     * @param x
     * @param y
     * @return int value
     */
    public int getCellValue(int x, int y) {
        return grid[x][y].getValue();
    }
    
    public int getNumberOfMines() {
        return mines;
    }
    
    /**
     * Returns the number of flagged cells at the moment
     * @return int value
     */
    public int getNumberOfFlaggedCells() {
        int sumOfFlags = 0;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].isFlagged()) {
                    sumOfFlags++;
                }
            }
        }
        
        return sumOfFlags;
    }
    
    public boolean isGameOnGoing() {
        return this.gameOnGoing;
    }
    
    public void startGameSession() {
        this.gameOnGoing = true;
    }
    
    public void stopGameSession() {
        this.gameOnGoing = false;
    }
    
    /**
     * Checks if the cell in (x,y) is opened
     * @param x
     * @param y
     * @return true if opened, false if not
     */
    public boolean cellIsOpened(int x, int y) {
        return grid[x][y].isOpened();
    }
}
