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
import javafx.geometry.Insets;
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
    private static Label topSize10;
    private static Label topSize16;
    private static Label topSize20;
    private static Label topSize30;
    private static Stage stage;
    private static Scene highscoreScene;
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
    public void start(Stage stage1) throws Exception {
        this.stage = stage1;
        
        //fetching the graphics
        one = new Image("/graphics/one.png",20,20,true,true);
        two = new Image("/graphics/two.png",20,20,true,true);
        three = new Image("/graphics/three.png",20,20,true,true);
        four = new Image("/graphics/four.png",20,20,true,true);
        five = new Image("/graphics/five.png",20,20,true,true);
        six = new Image("/graphics/six.png",20,20,true,true);
        seven = new Image("/graphics/seven.png",20,20,true,true);
        eight = new Image("/graphics/eight.png",20,20,true,true);
        flag = new Image("/graphics/flag.png",20,20,true,true);
        wrongFlag = new Image("/graphics/wrongFlag.png",20,20,true,true);
        mine = new Image("/graphics/mine.png",20,20,true,true);
        angryMine = new Image("/graphics/angryMine.png",20,20,true,true);
        
        stage.setTitle("Minesweeper");
        
        
        //MAIN MENU
        BorderPane menu = new BorderPane();
        GridPane gameModes = new GridPane();
        
        //setting some space between and around the buttons
        gameModes.setVgap(10);
        gameModes.setHgap(10);
        gameModes.setPadding(new Insets(10,10,10,10));
        
        //creating and adding the gamebuttons
        Button playExtraSmallButton = new Button("Play 10 x 10");
        Button playSmallButton = new Button("Play 16 x 16");
        Button playMediumButton = new Button("Play 20 x 20");
        Button playBigButton = new Button("Play 30 x 30");
        gameModes.add(playExtraSmallButton, 1, 1);
        gameModes.add(playSmallButton, 2, 1);
        gameModes.add(playMediumButton, 1, 2);
        gameModes.add(playBigButton, 2, 2);
        
        //Highscore-button in the center and setting some space around it
        Button highscoreButton = new Button("Highscores");
        BorderPane centerHighscore = new BorderPane();
        centerHighscore.setCenter(highscoreButton);
        centerHighscore.setPadding(new Insets(10,0,10,0));
        
        //The final layout
        menu.setTop(gameModes);
        menu.setBottom(centerHighscore);
        Scene scene = new Scene(menu);
        
        
        //LAYOUT OF THE GAMEMODE
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
        
        newGame(gridSize);
        layout.setCenter(gridPane);
        Scene gameScene = new Scene(layout);
        
        
        //LAYOUT OF HIGHSCORES
        VBox highscoreLayout = new VBox();
        GridPane listOfScores = new GridPane();
        Button goToMenu = new Button("Back to menu");
        
        highscoreLayout.setSpacing(10);
        highscoreLayout.getChildren().add(new Label("HIGHSCORES"));
        highscoreLayout.getChildren().add(listOfScores);
        highscoreLayout.getChildren().add(goToMenu);
        
        topSize10 = new Label(game.getTop10(10));
        topSize16 = new Label(game.getTop10(16));
        topSize20 = new Label(game.getTop10(20));
        topSize30 = new Label(game.getTop10(30));
        listOfScores.add(topSize10,1,1);
        listOfScores.add(topSize16,2,1);
        listOfScores.add(topSize20,1,2);
        listOfScores.add(topSize30,2,2);
        listOfScores.setHgap(10);
        listOfScores.setVgap(10);
        listOfScores.setPadding(new Insets(10,10,10,10));
        
        highscoreScene = new Scene(highscoreLayout);
        
        
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
        
        playExtraSmallButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridSize = 10;
                buttons = new Button[gridSize][gridSize];
                newGame(gridSize);
                stage.setScene(gameScene);
            }
        });
        
        playSmallButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridSize = 16;
                buttons = new Button[gridSize][gridSize];
                newGame(gridSize);
                stage.setScene(gameScene);
            }
        });
        playMediumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridSize = 20;
                buttons = new Button[gridSize][gridSize];
                newGame(gridSize);
                stage.setScene(gameScene);
            }
        });
        
        playBigButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridSize = 30;
                buttons = new Button[gridSize][gridSize];
                newGame(gridSize);
                stage.setScene(gameScene);
            }
        });
        
        highscoreButton.setOnAction((event) -> {
            stage.setScene(highscoreScene);
        });
        
        stage.setScene(scene);
        stage.show();
    }
    
     public static void newGame(int size) {
        game = new MinesweeperGame(size, "highscore.db");
        text.setText("mines: " + game.getNumberOfMines() + "\nflags: 0");
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
        if (!game.isGameOnGoing()) {
            game.startGame();
        }
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
                            game.stopGame();
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
                    text.setText("mines: " + game.getNumberOfMines() + "\nflags: " + game.getNumberOfFlags());
                    buttons[i][j].setGraphic(ivFlag);
                }
                if (!game.cellIsFlagged(i, j) && !game.cellIsOpened(i,j)) {
                    buttons[i][j].setGraphic(null);
                }
            }
        }
        
        //checking if the player has won the game
        if (game.checkIfWon()) {
            game.stopGame();
            showMines();
            text.setText("You won!");
            
            Stage st = new Stage();
            VBox lay = new VBox();
            Button saveButton = new Button("Save");
            TextField textField = new TextField();
            lay.setSpacing(10);
            lay.getChildren().add(new Label("Your name:"));
            lay.getChildren().add(textField);
            lay.getChildren().add(saveButton);
            Scene sc = new Scene(lay);
            
            saveButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String name = textField.getText();
                    if (name.equals("")) {
                        name = "unknown";
                    }
                    game.saveHighscore(gridSize,name);
                    st.close();
                    topSize10.setText(game.getTop10(10));
                    topSize16.setText(game.getTop10(16));
                    topSize20.setText(game.getTop10(20));
                    topSize30.setText(game.getTop10(30));
                    stage.setScene(highscoreScene);
                    newGame(gridSize);
                }
            }); 
            
            
            st.setTitle("Save your score!");
            st.setScene(sc);
            st.show();
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
