/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

import java.sql.*;
import java.util.*;
/**
 *
 * @author hiira
 */
public class HighscoreDAO {
    private String dbname;
    
    /**
     * Contstructor: creates the tables in the database 
     * @param dbname the name of the database
     */
    public HighscoreDAO(String dbname) {
        this.dbname = dbname;
        createTables();
    }
    
    /* Creates table(s) to the database if they do not exist already
    */
    public void createTables() {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.dbname)) {
            Statement s = db.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Highscores "
                    + "(id INTEGER PRIMARY KEY, gridsize INTEGER, time DOUBLE, name TEXT);");
        } catch (SQLException e) {
            getErrorMessages(e);
        }
    }
    
   /**
    * Saves a new highscore to the database
    * @param gridsize the size of the grid
    * @param time the time that went into the game
    * @param name the name of the player
    */
    public void saveHighscore(int gridsize, double time, String name) {
        createTables();
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.dbname)) {
            
            PreparedStatement s = db.prepareStatement("INSERT INTO Highscores "
                     + "(gridsize,time,name) VALUES (?,?,?);");
            s.setInt(1, gridsize);
            s.setDouble(2, time);
            s.setString(3, name);
            s.executeUpdate();

            
        } catch (SQLException e) {
            getErrorMessages(e);
        }
    }
    
    /**
     * Returns a String-array of the top 10 highscores in the given gamemode (gridsize)
     * @param gridsize
     * @return String[] containing the highscores
     */
    public String[] getTop10(int gridsize) {
        String[] top10 = new String[10];
        
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.dbname)) {
            PreparedStatement s = db.prepareStatement("SELECT * FROM Highscores WHERE gridsize=? ORDER BY time LIMIT 10;");
            
            s.setInt(1, gridsize);
            ResultSet rs = s.executeQuery();
            
            int i = 0;
            while (rs.next()) {
                double gametime = rs.getDouble("time");
                String timeInMinsAndSecs = convertToMinutesAndSeconds(gametime);
                top10[i] = rs.getString("id") + "," + timeInMinsAndSecs + "," + rs.getString("name");
                i++;
            }
            
        } catch (SQLException e) {
            getErrorMessages(e);
        }
        
        return top10;
    }
    
    /**
     * Method for converting the given time to proper (String) format
     * @param gametime the gametime in seconds
     * @return String in time format "x m y sec"
     */
    public String convertToMinutesAndSeconds(double gametime) {
        int minutes = (int) gametime / 60;
        long seconds = Math.round(gametime % 60);
        String timeInMinsAndSecs = String.valueOf(minutes) + " m " + String.valueOf(seconds) + " sec";
        return timeInMinsAndSecs;        
    }
    
    /**
     * Deletes the tables from the database, if tables exist
     */
    public void deleteTables() {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.dbname)) {
            Statement s = db.createStatement();
            s.execute("DROP TABLE IF EXISTS Highscores");
        } catch (SQLException e) {
            getErrorMessages(e);
        }
    }
    
    /*
     * Method for error messages
     */
    public void getErrorMessages(SQLException e) {
        do {
            System.err.println("Viesti: " + e.getMessage());
            System.err.println("Virhekoodi: " + e.getErrorCode());
            System.err.println("SQL-tilakoodi: " + e.getSQLState());
        } while (e.getNextException() != null);
    }
}
