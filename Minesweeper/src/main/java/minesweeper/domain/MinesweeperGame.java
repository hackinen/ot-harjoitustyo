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
    private Highscore highscore;
    
    public MinesweeperGame(int gridSize, String dbname) {
        this.grid = new Grid(gridSize);
        this.highscore = new Highscore(dbname);
    }
    
    /**
     * Starts the game: highscore-object starts timing and grid is set on game-mode
     */
    public void startGame() {
        highscore.startTiming();
        grid.startGameSession();
    }
    
    /**
     * Ends the game: highscore-object stops timing and the grid is revealed
     */
    public void stopGame() {
        grid.stopGameSession();
        highscore.stopTiming();
        reveal();
    }
    
    /**
     * saves the new highscore to the database
     * 
     * @param gridSize  The size of the current grid
     * @param name The name of the player
     */
    public void saveHighscore(int gridSize, String name) {
        double time = highscore.getGameTime();
        highscore.saveHighscore(gridSize, name);
    }
    
    /**
     * Reveals the grid's mines and wrong flags using Grid's method revealTheGrid
     */
    public void reveal() {
        grid.revealTheGrid();
    }
    
    /**
     * Sets the cell in coordinates (x,y) as an angry mine (a mine that's been opened)
     * @param x
     * @param y 
     */
    public void setAngryMine(int x, int y) {
        grid.setAngryMine(x, y);
    }
    
    /**
     * Flags the cell in coordinates (x,y)
     * 
     * @param x
     * @param y 
     */
    public void flagCell(int x, int y) {
        grid.getCell(x, y).flag();
    }
    
    /**
     * sets the cell in coordinates (x,y) as opened
     * 
     * @param x
     * @param y 
     */
    public void openCell(int x, int y) {
        grid.openCell(x, y);
    }
    
    public boolean isGameOnGoing() {
        return grid.isGameOnGoing();
    }
    
    /**
     * Checks if the cell in (x,y) is opened
     * @param x
     * @param y
     * @return true if opened, false otherwise
     */
    public boolean cellIsOpened(int x, int y) {
        return grid.cellIsOpened(x, y);
    }
    
    /**
     * Checks if the cell in (x,y) is flagged
     * @param x
     * @param y
     * @return true if flagged, false otherwise
     */
    public boolean cellIsFlagged(int x, int y) {
        return grid.getCell(x, y).isFlagged();
    }
    
    /**
     * Checks if the cell in (x,y) is an angry mine (a mine that has been opened)
     * @param i
     * @param j
     * @return true if the cell is set as angry mine, false otherwise
     */
    public boolean cellIsAngryMine(int i, int j) {
        return grid.getCell(i, j).isAngryMine();
    }
    
    /**
     * Checks if the cell in (x,y) has been flagged wrongly
     * @param i
     * @param j
     * @return true if flagged wrong, false otherwise
     */
    public boolean cellIsFlaggedWrong(int i , int j) {
        return grid.getCell(i, j).isFlaggedWrong();
    }
    
    /**
     * Checks if the game has been won: if all the numbered cells have been opened
     * @return true if all the number-cells are open, false otherwise
     */
    public boolean checkIfWon() {
        boolean areThereUnopenedNumbers = false;
        for (int x = 0; x < grid.getGridSize(); x++) {
            for (int y = 0; y < grid.getGridSize(); y++) {
                if (grid.getCellValue(x, y) < 9 && grid.getCellValue(x, y) != 0 && !grid.getCell(x, y).isOpened()) {
                    areThereUnopenedNumbers = true;
                }
                if (areThereUnopenedNumbers) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Returns the top 10 highscores from the database, where the gamemode is gridsize x gridsize
     * @param gridSize the gamemode size
     * @return a long String containing all the highscores
     */
    public String getTop10(int gridSize) {
        return highscore.getTop10(gridSize);
    }
    
    /**
     * Returns the value (0-9) of the cell in (x,y)
     * @param x
     * @param y
     * @return int value
     */
    public int getValueOfCell(int x, int y) {
        return grid.getCellValue(x, y);
    }
    
    /**
     * Returns the number of mines in the game
     * @return int value
     */
    public int getNumberOfMines() {
        return grid.getNumberOfMines();
    }
    
    /**
     * Returns the current number of flagged cells
     * @return int value
     */
    public int getNumberOfFlags() {
        return grid.getNumberOfFlaggedCells();
    }
    
    public Grid getGrid() {
        return this.grid;
    }
    
    public Highscore getHighscore() {
        return this.highscore;
    }
    
    
    
}
