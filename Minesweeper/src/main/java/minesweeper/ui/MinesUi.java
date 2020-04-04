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
    private static HBox hbox;
    private static Label text;
    private static MinesweeperGame game;
    
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
        
        //layout of the main menu
        BorderPane menu = new BorderPane();
        Button playButton = new Button("Play");
        Button highscoreButton = new Button("Highscores");
        menu.setTop(playButton);
        menu.setBottom(highscoreButton);
        Scene scene = new Scene(menu);
        
        
        //layoyt of the game
        BorderPane layout = new BorderPane();
        Button newGameButton = new Button("new game");
        Button backToMenuButton = new Button("menu");
        text = new Label("");
        gridPane = new GridPane();
        hbox = new HBox();
        hbox.setSpacing(10);
        layout.setTop(hbox);
        hbox.getChildren().add(newGameButton);
        hbox.getChildren().add(backToMenuButton);
        hbox.getChildren().add(text);
        //new game
        newGame(gridSize);
        layout.setCenter(gridPane);
        Scene gameScene = new Scene(layout);
        
        
        //layout of highscores
        VBox highscoreLayout = new VBox();
        BorderPane listOfScores = new BorderPane();
        
        Button goToMenu = new Button("Back to menu");
        
        
        highscoreLayout.setSpacing(10);
        highscoreLayout.getChildren().add(new Label("HIGHSCORES"));
        highscoreLayout.getChildren().add(listOfScores);
        highscoreLayout.getChildren().add(goToMenu);
        
        //listOfScores.setCenter();
        
        Scene highscoreScene = new Scene(highscoreLayout);
        
        
        
        //new game button eventHandler
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newGame(gridSize);
            }
        });
        
       
        backToMenuButton.setOnAction((event) -> {
            stage.setScene(scene);
        });
        
        goToMenu.setOnAction((event) -> {
            stage.setScene(scene);
        });
        
        playButton.setOnAction((event) -> {
            stage.setScene(gameScene);
        });
        
        highscoreButton.setOnAction((event) -> {
            stage.setScene(highscoreScene);
        });
        
        stage.setScene(scene);
        stage.show();
    }
    
     public static void newGame(int size) {
        game = new MinesweeperGame(size);
        text.setText("");
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

                        if (btn==MouseButton.PRIMARY) {
                            actionOnMouseClick(xf,yf);
                        } 
                        if (btn==MouseButton.SECONDARY) {
                            actionOnRightMouseClick(xf,yf);
                        }

                       
                    }
                });
            }
        }
    }
    
    public static void actionOnMouseClick(int x, int y) {
        game.openCell(x,y);
        update();
    }
    
    public static void actionOnRightMouseClick(int x,int y) {
        game.flagCell(x,y);
        update();
    }
    
    public static void update() {
        
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                if (game.cellIsOpened(i,j)) {
                    buttons[i][j].setDisable(true);
                    switch (game.getValueOfCell(i,j)) {
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
                            game.setAngryMine(i, j);
                            game.reveal();
                            showMines();
                            text.setText("You lost :(");
                            return;
                        case 0:
                            buttons[i][j].setDisable(true);
                            break;
                        default:
                            break;
                    }
                }
                if (game.cellIsFlagged(i, j)) {
                    ImageView ivFlag = new ImageView(flag);
                    buttons[i][j].setGraphic(ivFlag);
                }
                if (!game.cellIsFlagged(i, j) && !game.cellIsOpened(i,j)) {
                    buttons[i][j].setGraphic(null);
                }
            }
        }
        
        //checking if the player has won the game
        if (game.checkIfWon()) {
            game.reveal();
            showMines();
            text.setText("You won!");
            return;
        }
        
        gridPane.requestFocus();
    }
    
    public static void showMines() {
        
        for (int i=0; i<gridSize; i++) {
            for (int j=0; j<gridSize; j++) {
                
                buttons[i][j].setDisable(true);
                
                if (game.cellIsOpened(i,j)) {
                    
                    if (game.getValueOfCell(i,j)==9 && !game.cellIsAngryMine(i, j)) {
                        ImageView ivMine = new ImageView(mine);
                        buttons[i][j].setGraphic(ivMine);
                    }
                    
                    if (game.cellIsFlaggedWrong(i, j)) {
                        ImageView ivWrongFlag = new ImageView(wrongFlag);
                        buttons[i][j].setGraphic(ivWrongFlag);
                    }
                }
            }
        }
    }
    
   
    
}
