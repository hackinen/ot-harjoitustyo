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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    

    public static void main(String[] args) {
        launch(MinesUi.class);
    }
    
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
        newGame(gridSize);
        
        //new game button eventHandler
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newGame(gridSize);
            }
        });
        
        layout.setCenter(gridPane);
  
        Scene scene = new Scene(layout);
        
        stage.setScene(scene);
        stage.show();
    }
    
     public static void newGame(int size) {
        grid = new Grid(size);
        gridSize=size;
        gridPane.getChildren().clear();
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
                buttons[x][y].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton btn = event.getButton();
                        if (grid.isGameOnGoing()) {
                            if (btn==MouseButton.PRIMARY) {
                                actionOnMouseClick(xf,yf);
                            } 
                            if (btn==MouseButton.SECONDARY) {
                                actionOnRightMouseClick(xf,yf);
                            }
                        }
                       
                    }
                });
            }
        }
    }
    
    public static void actionOnMouseClick(int x, int y) {
        grid.openCell(x,y);
        update();
    }
    
    public static void actionOnRightMouseClick(int x,int y) {
        grid.getCell(x, y).flag();
        update();
    }
    
    public static void update() {
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                if (grid.cellIsOpened(i,j)) {
                    buttons[i][j].setDisable(true);
                    switch (grid.getCellValue(i, j)) {
                        case 1:
                            ImageView ivOne = new ImageView(one);
                            buttons[i][j].setGraphic(ivOne);
                            break;
                        case 2:
                            ImageView ivTwo = new ImageView(two);
                            buttons[i][j].setGraphic(ivTwo);
                            break;
                        case 3:
                            ImageView ivThree = new ImageView(three);
                            buttons[i][j].setGraphic(ivThree);
                            break;
                        case 4:
                            ImageView ivFour = new ImageView(four);
                            buttons[i][j].setGraphic(ivFour);
                            break;
                        case 5:
                            ImageView ivFive = new ImageView(five);
                            buttons[i][j].setGraphic(ivFive);
                            break;
                        case 6:
                            ImageView ivSix = new ImageView(six);
                            buttons[i][j].setGraphic(ivSix);
                            break;
                        case 7:
                            ImageView ivSeven = new ImageView(seven);
                            buttons[i][j].setGraphic(ivSeven);
                            break;
                        case 8:
                            ImageView ivEight = new ImageView(eight);
                            buttons[i][j].setGraphic(ivEight);
                            break;
                        case 9:
                            ImageView ivAngryMine = new ImageView(angryMine);
                            buttons[i][j].setGraphic(ivAngryMine);
                            grid.revealTheGrid(i,j);
                            showMines();
                            return;
                        case 0:
                            buttons[i][j].setDisable(true);
                            break;
                        default:
                            break;
                    }
                }
                if (grid.getCell(i, j).isFlagged()) {
                    ImageView ivFlag = new ImageView(flag);
                    buttons[i][j].setGraphic(ivFlag);
                }
                if (!grid.getCell(i, j).isFlagged() && !grid.getCell(i, j).isOpened()) {
                    buttons[i][j].setGraphic(null);
                }
            }
        }
        gridPane.requestFocus();
    }
    
    public static void showMines() {
        
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                
                buttons[i][j].setDisable(true);
                
                if (grid.cellIsOpened(i,j)) {
                    
                    if (grid.getCellValue(i, j)==9 && !grid.getCell(i, j).isAngryMine()) {
                        ImageView ivMine = new ImageView(mine);
                        buttons[i][j].setGraphic(ivMine);
                    }
                    
                    if (grid.getCell(i, j).isFlaggedWrong()) {
                        ImageView ivWrongFlag = new ImageView(wrongFlag);
                        buttons[i][j].setGraphic(ivWrongFlag);
                    }
                }
            }
        }
    }
    
   
    
}
