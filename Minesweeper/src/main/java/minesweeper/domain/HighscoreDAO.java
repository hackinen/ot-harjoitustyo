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
    
    public HighscoreDAO(String dbname) {
        this.dbname = dbname;
        createTables();
    }
    
    //creates table(s) to the database
    public void createTables() {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.dbname)) {
            Statement s = db.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Highscores "
                    + "(id INTEGER PRIMARY KEY, gridsize INTEGER, time DOUBLE, name TEXT);");
        } catch (SQLException e) {
            getErrorMessages(e);
        }
    }
    
   
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
    
    public String convertToMinutesAndSeconds(double gametime) {
        int minutes = (int) gametime / 60;
        long seconds = Math.round(gametime % 60);
        String timeInMinsAndSecs = String.valueOf(minutes) + " m " + String.valueOf(seconds) + " sec";
        return timeInMinsAndSecs;        
    }
    
    public void deleteTables() {
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:" + this.dbname)) {
            Statement s = db.createStatement();
            s.execute("DROP TABLE IF EXISTS Highscores");
        } catch (SQLException e) {
            getErrorMessages(e);
        }
    }
    
    public void printDatabase() {
        try (Connection db = DriverManager.getConnection(("jdbc:sqlite:" + this.dbname))) {
            
            Statement s = db.createStatement();
            
            ResultSet r = s.executeQuery("SELECT * FROM Highscores");
            System.out.println("Highscores");
            System.out.println("");
            while (r.next()) {
                System.out.println(r.getInt("id") + "  " + r.getInt("gridsize") + "  " + r.getDouble("time") + " s  " + r.getString("name"));
            }
            System.out.println("");
            
            
        } catch (SQLException e) {
            getErrorMessages(e);
        }
    }
   
    
    public void getErrorMessages(SQLException e) {
        do {
            System.err.println("Viesti: " + e.getMessage());
            System.err.println("Virhekoodi: " + e.getErrorCode());
            System.err.println("SQL-tilakoodi: " + e.getSQLState());
        } while (e.getNextException() != null);
    }
}
