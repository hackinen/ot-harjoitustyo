/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;

import minesweeper.domain.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.image.*;
/**
 *
 * @author hiira
 */
public class MinesUi extends Application {
    private static int width = 800;
    private static int height = 600;
    private static int gridSize = 10;
    private static Button[][] buttons = new Button[gridSize+1][gridSize+1];
    
    //graphics
    private static Image one;
    private static Image two;
    private static Image three;
    private static Image four;
    private static Image five;
    private static Image six;
    private static Image seven;
    private static Image eight;
    private static Image flag;
    private static Image wrongFlag;
    private static Image mine;
    private static Image angryMine;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        //fetching the graphics
        one = new Image("file:graphics/one.png",20,20,true,true);
        two = new Image("graphics/two.png",20,20,true,true);
        three = new Image("graphics/three.png",20,20,true,true);
        four = new Image("graphics/four.png",20,20,true,true);
        five = new Image("graphics/five.png",20,20,true,true);
        six = new Image("graphics/six.png",20,20,true,true);
        seven = new Image("graphics/seven.png",20,20,true,true);
        eight = new Image("graphics/eight.png",20,20,true,true);
        flag = new Image("graphics/flag.png",20,20,true,true);
        wrongFlag = new Image("graphics/wrongFlag.png",20,20,true,true);
        mine = new Image("graphics/mine.png",20,20,true,true);
        angryMine = new Image("graphics/angryMine.png",20,20,true,true);
        
        stage.setTitle("Minesweeper");
        
        //layoyt of the game
        BorderPane layout = new BorderPane();
        Button newGameButton = new Button("new game");
        GridPane grid = new GridPane();
        layout.setTop(newGameButton);
        
        //creating the grid and adding the buttons
        for (int x=1; x<11; x++) {
            for (int y=1; y<11; y++) {
                Button button = new Button("");
                grid.add(button, x, y);
                buttons[x][y]=button;
                button.setMaxSize(25, 25);
                button.setMinSize(25, 25);
                button.setStyle("-fx-focus-color: transparent ; -fx-faint-focus-color: transparent ;");
                
                //defining the action on mouseclick
                button.setOnAction((event) -> {
                   ImageView iv = new ImageView(one);
                   button.setGraphic(iv);
                   button.setDisable(true);
                   grid.requestFocus();
                });
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
