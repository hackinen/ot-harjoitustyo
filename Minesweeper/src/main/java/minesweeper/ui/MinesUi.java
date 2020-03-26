/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;

import minesweeper.domain.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        two = new Image("file:graphics/two.png",20,20,true,true);
        three = new Image("file:graphics/three.png",20,20,true,true);
        four = new Image("file:graphics/four.png",20,20,true,true);
        five = new Image("file:graphics/five.png",20,20,true,true);
        six = new Image("file:graphics/six.png",20,20,true,true);
        seven = new Image("file:graphics/seven.png",20,20,true,true);
        eight = new Image("file:graphics/eight.png",20,20,true,true);
        flag = new Image("file:graphics/flag.png",20,20,true,true);
        wrongFlag = new Image("file:graphics/wrongFlag.png",20,20,true,true);
        mine = new Image("file:graphics/mine.png",20,20,true,true);
        angryMine = new Image("file:graphics/angryMine.png",20,20,true,true);
        
        stage.setTitle("Minesweeper");
        
        //layoyt of the game
        BorderPane layout = new BorderPane();
        Button newGameButton = new Button("new game");
        GridPane gridPane = new GridPane();
        layout.setTop(newGameButton);
        //new game
        Grid grid = new Grid(10);
        
        //creating the grid and adding the buttons
        for (int x=1; x<11; x++) {
            for (int y=1; y<11; y++) {
                Button button = new Button("");
                gridPane.add(button, x, y);
                buttons[x][y]=button;
                button.setMaxSize(25, 25);
                button.setMinSize(25, 25);
                button.setStyle("-fx-focus-color: transparent ; -fx-faint-focus-color: transparent ;");
                
                final int xf=x;
                final int yf=y;
                
                //defining the action on mouseclick
                button.setOnAction(new EventHandler<ActionEvent>() {
                   public void handle(ActionEvent event) {
                       actionOnMouseClick(button,gridPane,grid,xf,yf);
                   }
                });
            }
        }
        
        
        
        layout.setCenter(gridPane);
        
        
        
        Scene scene = new Scene(layout);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void actionOnMouseClick(Button button, GridPane gridPane, Grid grid, int x, int y) {
        ImageView ivOne = new ImageView(one);
        ImageView ivTwo = new ImageView(two);
        ImageView ivThree = new ImageView(three);
        ImageView ivFour = new ImageView(four);
        ImageView ivFive = new ImageView(five);
        ImageView ivSix = new ImageView(six);
        ImageView ivSeven = new ImageView(seven);
        ImageView ivEight = new ImageView(eight);
        ImageView ivAngryMine = new ImageView(angryMine);
        
        if (grid.getCellValue(x, y)==1) {
            button.setGraphic(ivOne);
        } else if (grid.getCellValue(x, y)==2) {
            button.setGraphic(ivTwo);
        } else if (grid.getCellValue(x, y)==3) {
            button.setGraphic(ivThree);
        } else if (grid.getCellValue(x, y)==4) {
            button.setGraphic(ivFour);
        } else if (grid.getCellValue(x,y)==5) {
            button.setGraphic(ivFive);
        } else if (grid.getCellValue(x, y)==6) {
            button.setGraphic(ivSix);
        } else if (grid.getCellValue(x, y)==7) {
            button.setGraphic(ivSeven);
        } else if (grid.getCellValue(x, y)==8) {
            button.setGraphic(ivEight);
        } else if (grid.getCellValue(x, y)==9) {
            button.setGraphic(ivAngryMine);
        }
        
        button.setDisable(true);
        gridPane.requestFocus();
    }
    
    public static void main(String[] args) {
        launch(MinesUi.class);
    }
}
