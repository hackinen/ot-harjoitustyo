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
    private static Button[][] buttons = new Button[gridSize][gridSize];
    private static GridPane gridPane;
    private static Grid grid;
    
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
        gridPane = new GridPane();
        layout.setTop(newGameButton);
        //new game
        grid = new Grid(gridSize);
        
        //creating the grid and adding the buttons
        for (int x=0; x<gridSize; x++) {
            for (int y=0; y<gridSize; y++) {
                Button button = new Button("");
                buttons[x][y]=button;
                gridPane.add(buttons[x][y], y, x);
                
                button.setMaxSize(25, 25);
                button.setMinSize(25, 25);
                button.setStyle("-fx-focus-color: transparent ; -fx-faint-focus-color: transparent ;");
                
                final int xf=x;
                final int yf=y;
                
                //defining the action on mouseclick
                button.setOnAction(new EventHandler<ActionEvent>() {
                   public void handle(ActionEvent event) {
                       actionOnMouseClick(xf,yf);
                   }
                });
            }
        }

        
        layout.setCenter(gridPane);
  
        Scene scene = new Scene(layout);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void actionOnMouseClick(int x, int y) {
        ImageView ivOne = new ImageView(one);
        ImageView ivTwo = new ImageView(two);
        ImageView ivThree = new ImageView(three);
        ImageView ivFour = new ImageView(four);
        ImageView ivFive = new ImageView(five);
        ImageView ivSix = new ImageView(six);
        ImageView ivSeven = new ImageView(seven);
        ImageView ivEight = new ImageView(eight);
        ImageView ivAngryMine = new ImageView(angryMine);

        System.out.println(grid.toString());
        switch (grid.getCellValue(x, y)) {
            case 1:
                buttons[x][y].setGraphic(ivOne);
                break;
            case 2:
                buttons[x][y].setGraphic(ivTwo);
                break;
            case 3:
                buttons[x][y].setGraphic(ivThree);
                break;
            case 4:
                buttons[x][y].setGraphic(ivFour);
                break;
            case 5:
                buttons[x][y].setGraphic(ivFive);
                break;
            case 6:
                buttons[x][y].setGraphic(ivSix);
                break;
            case 7:
                buttons[x][y].setGraphic(ivSeven);
                break;
            case 8:
                buttons[x][y].setGraphic(ivEight);
                break;
            case 9:
                buttons[x][y].setGraphic(ivAngryMine);
                break;
            case 0:
                openUntilNotEmpty(x,y);
            default:
                break;
        }
        
        buttons[x][y].setDisable(true);
        gridPane.requestFocus();
    }
    
    public static void openUntilNotEmpty(int x, int y) {
        if (x<0 || y<0 || x>=gridSize || y>=gridSize) {
            return;
        }
        if (buttons[x][y].isDisabled()) {
            return;
        }
        if (grid.getCellValue(x,y)!=0 && grid.getCellValue(x, y)<9) {
            actionOnMouseClick(x,y);
            return;
        }
        buttons[x][y].setDisable(true);
        openUntilNotEmpty(x-1,y);
        openUntilNotEmpty(x-1,y+1);
        openUntilNotEmpty(x,y+1);
        openUntilNotEmpty(x+1,y+1);
        openUntilNotEmpty(x+1,y);
        openUntilNotEmpty(x+1,y-1);
        openUntilNotEmpty(x,y-1);
        openUntilNotEmpty(x-1,y-1);
    }
    
    public static void main(String[] args) {
        launch(MinesUi.class);
    }
}
