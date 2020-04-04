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
public class MinesweeperGame {
    private Grid grid;
    private int gridSize;
    private Highscore highscore;
    
    public MinesweeperGame(int gridSize) {
        this.grid = new Grid(gridSize);
        this.gridSize=gridSize;
        this.highscore = new Highscore("highscore.db");
    }
    
    public void startGame() {
        highscore.startTiming();
        grid.startGameSession();
    }
    
    public void stopGame() {
        grid.stopGameSession();
        highscore.stopTiming();
        reveal();
    }
    
    public void saveHighscore(String name) {
        double time = highscore.getGameTime();
        highscore.saveHighscore(name);
    }
    
    public String getTop10() {
        return highscore.getTop10();
    }
    
    public void reveal() {
        grid.revealTheGrid();
    }
    
    public void setAngryMine(int x, int y) {
        grid.setAngryMine(x, y);
    }
    
    public void flagCell(int x, int y) {
        grid.getCell(x, y).flag();
    }
    
    public void openCell(int x, int y) {
        grid.openCell(x,y);
    }
    
    public int getValueOfCell(int x, int y) {
        return grid.getCellValue(x, y);
    }
    
    public boolean isGameOnGoing() {
        return grid.isGameOnGoing();
    }
    
    public boolean cellIsOpened(int x, int y) {
        return grid.cellIsOpened(x, y);
    }
    
    public boolean cellIsFlagged(int x, int y) {
        return grid.getCell(x, y).isFlagged();
    }
    
    public boolean cellIsAngryMine(int i, int j) {
        return grid.getCell(i, j).isAngryMine();
    }
    
    public boolean cellIsFlaggedWrong(int i , int j) {
        return grid.getCell(i, j).isFlaggedWrong();
    }
    
    public boolean checkIfWon() {
        boolean areThereUnopenedNumbers = false;
        for (int x=0; x<gridSize; x++) {
            for (int y=0; y<gridSize; y++) {
                if (grid.getCellValue(x,y)<9 && grid.getCellValue(x,y)!=0 && !grid.getCell(x,y).isOpened()) {
                    areThereUnopenedNumbers=true;
                }
                if (areThereUnopenedNumbers) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
