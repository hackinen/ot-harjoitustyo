/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
/**
 *
 * @author hiira
 */
public class MinesUi extends Application {
    private static int width = 800;
    private static int height = 600;
    private static int gridSize = 10;
    private static Button[][] buttons = new Button[gridSize+1][gridSize+1];
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Minesweeper");
        
        //layoyt of the game
        BorderPane layout = new BorderPane();
        Button newGameButton = new Button("new game");
        GridPane grid = new GridPane();
        layout.setTop(newGameButton);
        
        //creating the grid and adding the buttons
        for (int x=1; x<11; x++) {
            for (int y=1; y<11; y++) {
                Button button = new Button("  ");
                grid.add(button, x, y);
                buttons[x][y]=button;
                
                //defining the action on mouseclick
                
            }
        }
        layout.setCenter(grid);
        
        
        
        Scene scene = new Scene(layout);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(MinesUi.class);
    }
}
