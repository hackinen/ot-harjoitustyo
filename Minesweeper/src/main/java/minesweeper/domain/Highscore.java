/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;



public class Highscore {
    private HighscoreDAO dao;
    private long startTime;
    private long endTime;
    
    /**
     * Constructor: creates the dao-object (that creates the database) with the name given as a parameter
     * @param nameOfDatabase the name of the database that will store all the highscores
     */
    public Highscore(String nameOfDatabase) {
        this.startTime = 0;
        this.endTime = 0;
        this.dao = new HighscoreDAO(nameOfDatabase);
    }
    
    /**
     * Starts the timing of the game
     */
    public void startTiming() {
        this.startTime = System.nanoTime();
    }
    
    /**
     * Stops the timing of the game
     */
    public void stopTiming() {
        this.endTime = System.nanoTime();
    }
    
    /**
     * Returns the time that went into the game session
     * @return double time (in seconds)
     */
    public double getGameTime() {
        long wholeTime = this.endTime - this.startTime;
        
        double gameTime = ((wholeTime) / 1e9) * 1.0;
        
        return gameTime;
    }
    
    /**
     * Saves the newly won game's highscore to the database with the given name
     * @param gridSize the size of the game that was won
     * @param name the name of the player
     */
    public void saveHighscore(int gridSize, String name) {
        double gametime = getGameTime();
        dao.saveHighscore(gridSize, gametime, name);
    }
    
    /**
     * Returns the top 10 best highscores of the gamemode that's size is gridSize * gridSize
     * @param gridSize the size of the game
     * @return String that contains the top 10 results
     */
    public String getTop10(int gridSize) {
        String[] top10 = dao.getTop10(gridSize);
        String results = gridSize + "x" + gridSize + ": \n";
        for (int i = 0; i < top10.length; i++) {
            if (top10[i] == null) {
                break;
            }
            String[] pieces = top10[i].split(",");
            
            String rank = String.valueOf(i + 1);
            results += rank + " --- " + pieces[1] + " --- " + pieces[2] + "\n";
        }
        
        results += "\n";
        
        return results;
    }
    
    public HighscoreDAO getDAO() {
        return this.dao;
    }
    
    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
}
