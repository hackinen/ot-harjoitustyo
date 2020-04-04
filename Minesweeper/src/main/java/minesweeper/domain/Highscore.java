/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;



public class Highscore {
    private DAO dao;
    private long startTime;
    private long endTime;
    private long pauseStartTime;
    private long pauseEndTime;
    private String name;
    
    public Highscore(String nameOfDatabase) {
        this.startTime=0;
        this.endTime=0;
        this.pauseEndTime=0;
        this.pauseStartTime=0;
        this.name="";
        
        this.dao = new DAO(nameOfDatabase);
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
    public void startTiming() {
        this.startTime = System.nanoTime();
    }
    
    public void stopTiming() {
        this.endTime = System.nanoTime();
    }
    /*
    public void startPause() {
        this.pauseStartTime=System.nanoTime();
    }
    
    public void endPause() {
        this.pauseEndTime=System.nanoTime();
    }
    */
    
    public double getGameTime() {
        //long pause = this.pauseEndTime-this.pauseStartTime;
        long wholeTime = this.endTime-this.startTime;
        
        double gameTime = ((wholeTime)/1e9)*1.0;
        
        return gameTime;
    }
    
    public void saveHighscore() {
        double gametime = getGameTime();
        dao.saveHighscore(gametime,name);
    }
    
    public void printTop10() {
        String[] top10 = dao.getTop10();
        
        for (int i=0; i<top10.length; i++) {
            if (top10[i]==null) {
                break;
            }
            String[] pieces = top10[i].split(",");
            
            System.out.println(pieces[0]+" --- "+pieces[1]+" --- "+pieces[2]);
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    
}
