package MainGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.util.Duration;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static String textTop = "Place your ships in the left hand grid.";
    private static String textBot = "";
	private static boolean shipsPlaced = false; // should be by default false
	private static boolean playerTurn = true;
	private static boolean difficulty = true;
	private static Grid player1Grid = new Grid();
	private static Grid player2Grid = new Grid();
    //used for the player's cursor
    private static Ship currentShip;
    private static int currentLength;
    //amount of ships player 1 has sunk
    private static int player1SunkShips = 0;
    //amount of ships the computer has sunk
	private static int computerSunkShips = 0;
	//winner, 0 = no winner, 1 = player, 2 = computer
    private static int winner = 0;
    public BotEasy bot = new BotEasy();

    public BotHard bot1 = new BotHard();

    public BotEasy bot2 = new BotEasy();


    @Override
    public void start(Stage primaryStage){

        //create ships
        ShipBattleship battleShip = new ShipBattleship();
        ShipDestroyer destroyerShip = new ShipDestroyer();
        ShipCarrier carrierShip = new ShipCarrier();
        ShipCruiser cruiserShip = new ShipCruiser();
        ShipSubmarine submarineShip = new ShipSubmarine();

        //add ships to stack
        Stack<Ship> shipStack = new Stack<>();
        shipStack.push(battleShip);
        shipStack.push(destroyerShip);
        shipStack.push(carrierShip);
        shipStack.push(cruiserShip);
        shipStack.push(submarineShip);

        currentShip = shipStack.peek();
        currentLength = currentShip.getLength();
        //size of window
        int width = 720;
        int height = 480;

        Cursor cursor = new Cursor(currentLength);
        Cursor cursor2 = new Cursor(1);

        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setMaxWidth(10);
        grid.setMaxHeight(10);

        GridPane grid2 = new GridPane();
        grid2.setHgap(1);
        grid2.setVgap(1);
        grid2.setMaxWidth(10);
        grid2.setMaxHeight(10);

        createGrid(grid);
        createGrid(grid2);

        //font for text in-game
        Font font;
        font = Font.font("Arial",FontWeight.BOLD,25);

        Text text1 = new Text(textTop);
        text1.setFont(font);

        Text text2 = new Text(textBot);
        text2.setFont(font);

        //hbox top and bot contain game text
        HBox hBoxTop = new HBox();
        hBoxTop.getChildren().add(text1);
        hBoxTop.setAlignment(Pos.CENTER);

        Group wholeScreen = new Group();
        Button backButton = new Button("Go Back");
        wholeScreen.getChildren().addAll(backButton);


        HBox hBoxBot = new HBox();
        hBoxBot.getChildren().add(text2);
        hBoxBot.setAlignment(Pos.CENTER);

        //contains two grid objects
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(grid,grid2);

        //create anchor pane and set anchors
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(hBoxTop,20.0);
        AnchorPane.setLeftAnchor(hBoxTop,10.0);
        AnchorPane.setRightAnchor(hBoxTop,10.0);

        AnchorPane.setLeftAnchor(hbox,10.0);
        AnchorPane.setRightAnchor(hbox,10.0);
        AnchorPane.setTopAnchor(hbox,250.0);

        AnchorPane.setBottomAnchor(hbox,250.0);
        AnchorPane.setBottomAnchor(hBoxBot,20.0);
        AnchorPane.setLeftAnchor(hBoxBot,10.0);
        AnchorPane.setRightAnchor(hBoxBot,10.0);

        //add hboxes to anchor pane
        anchorPane.getChildren().addAll(hBoxTop,hbox,hBoxBot, wholeScreen);
        //anchorPane.setPrefSize(width,height);
        Scene scene = new Scene(anchorPane,width,height);

        Font font1;
        font1 = Font.font("Verdana",FontWeight.BOLD,35);

        Text text3 = new Text("HARD");

        Text text4 = new Text("EASY");
        text4.setFont(font);
        Text text5 = new Text(" BATTLESHIP");
        text5.setFont(font1);
        Text text6 = new Text("CHOOSE DIFFICULTY");


        text6.setFont(font);

        // This pane is part of the Main menu
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20,20,20,20));
        pane.setHgap(50);
        pane.setVgap(50);
        pane.setStyle("-fx-background-color: LIGHTBLUE");
        pane.setAlignment(Pos.CENTER);

        Button button1 = new Button(text3.getText());

        button1.setPrefWidth(120);
        button1.setPrefHeight(100);
        button1.setStyle("");

        Button button2 = new Button(text4.getText());

        button2.setPrefWidth(120);
        button2.setPrefHeight(100);
        button1.setStyle("");

        // Adds to the Pane
        pane.add(button1, 2, 1);
        pane.add(button2, 0, 1);
        pane.add(text6, 1, 1);
        pane.add(text5, 1, 0);

        // this is the Main menu scene
        Scene scene1 = new Scene(pane, 720, 480);


        //updates game messages every 0.5 seconds
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            text1.setText(textTop);
            text2.setText(textBot);
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();


        grid.setOnMouseMoved(e -> {
			//doesn't allow interaction with second grid when ships are placed
        	if(shipsPlaced){
        		return;
			}
            double mx = e.getX();
            double my = e.getY();
            int mCellX = (int)mx/33;
            int mCellY = (int)my/33;

            if (mCellX < grid.getMaxWidth() && mCellY < grid.getMaxHeight()){
                cursor.cursorToMouse(grid, mCellX, mCellY);
            }

        } );

        grid.setOnMousePressed(e -> {
        	//doesn't allow interaction with second grid when ships are placed
        	if(shipsPlaced){
        		return;
			}
            if (!shipStack.isEmpty() && cursor.checkForShip(player1Grid) && !shipsPlaced){
        	    currentShip = shipStack.pop();
                cursor.placeShip(grid, player1Grid, currentShip);

                if (shipStack.size() > 0){
                    int newLength = shipStack.peek().getLength();
                    cursor.setCellLength(newLength);
                    //Removing the placed ship from stack, setting new cursor length
                    //shipStack.pop();
                }
            }

            if(shipStack.size() == 0){
        	    textTop = "";
        	    shipsPlaced = true;
        	    cursor.setCellLength(0);
            }
        });

        //on click of right hand grid
        grid2.setOnMousePressed(e -> {
            if(!shipsPlaced){
                return;
            }

            //if not the players turn it doesn't allow interaction
            if(!playerTurn){
                return;
            }

            //only run if there is no winner
            if (winner != 0){
                return;
            }

            //get location of activity
            double mX = e.getX();
            double mY = e.getY();
            int mCellX = (int)mX/33;
            int mCellY = (int)mY/33;

            textTop = "";

            //if in the grid color square to location of mouse
            if(inGrid(grid2,mCellX,mCellY)){
                cursor2.cursorToMouse(grid2,mCellX,mCellY);
            }

            //stops user from guessing same thing multiple times
            if(player2Grid.getGridContents(mCellX,mCellY).isHit()){
                textBot = "Error block already chosen.";
                return;
            }
            textBot = "";

            //performs actions to process guess
            //changes cell to hit status
            player2Grid.getGridContents(mCellX,mCellY).hit();

            //lowers hp if ship is in guess location
            if(player2Grid.getGridContents(mCellX,mCellY).getContainsShip()){
                Ship shipGuessed = player2Grid.getGridContents(mCellX,mCellY).getShip();
                shipGuessed.lowerHP();
                if (shipGuessed.getHP() == 0){
                    textTop = "You have sunk their " + shipGuessed.getName() +"!";
                    player1SunkShips += 1;
                    if (player1SunkShips >= 5){
                        winner = 1;
                        textTop = "Congratulations! You have won!";
                    }
                }
                else{
                    textTop = "Well done!";
                }
            }


            //updates grid
            colorGrid(grid2,player2Grid,false);

            playerTurn = false;//change turn
            computerGuess(player1Grid,grid);
        });

        //deals with rotation of ships
        scene.setOnKeyPressed(e ->{
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.R)){
                if(shipsPlaced){
                    return;
                }
                cursor.changeRotation();
                //get the (x,y) cells and put them back into the cursorToMouse function to update rotation instantly
                //...absolute hack, I am so sorry
                int tempCellX = cursor.getCellX();
                int tempCellY = cursor.getCellY();
                cursor.cursorToMouse(grid, tempCellX, tempCellY);
            }
        });

        // Button that goes to the normal game
        button1.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.show();
            difficulty = true;
        });
        // Runs the harder AI game
        button2.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.show();
            difficulty = false;
        });
        //  This button goes back to the main menu and resets and the value used in the game.
        backButton.setOnAction(e -> {
                    start(primaryStage);
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            player1Grid.getGridContents(j, i).setContainsShip(false);
                        }
                    }
                    shipsPlaced = false; // should be by default false
                    playerTurn = true;
                    player1Grid = new Grid();
                    player2Grid = new Grid();
                    winner = 0;
                    player1SunkShips = 0;
                    computerSunkShips = 0;
                    text1.setText("Place your ships in the left hand grid.");
                    text2.setText("");
                    BotEasy computer = new BotEasy();
                    computer.generateShips(player2Grid);
        });

        primaryStage.setTitle("BattleShip");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private void computerGuess(Grid g,GridPane vGrid) {
        //gets point
        Point p = new Point(12,12);
        if(difficulty){
            p = bot1.getGuess();
        }
        else if (!difficulty){
            p = bot2.getGuess();
        }
        g.getGridContents(p.getX(),p.getY()).hit();
        if(g.getGridContents(p.getX(),p.getY()).getContainsShip()){
            Ship guessedShip = g.getGridContents(p.getX(), p.getY()).getShip();
            guessedShip.lowerHP();
            bot1.setLastHit(true);
            if (guessedShip.getHP() == 0){
                textTop = "You have lost your " + guessedShip.getName() + "!";
                computerSunkShips +=1;
                if (computerSunkShips >= 5){
                    winner = 2;
                    textTop = "The computer has won! :(";
                }
            }
            else{
                textTop = "You've been hit! Fight back!";
            }
        }
        if(!g.getGridContents(p.getX(),p.getY()).getContainsShip()){
			bot1.setLastHit(false);
		}
        colorGrid(vGrid,g,true);
        playerTurn = true;
    }

    public boolean inGrid(GridPane g,int x,int y){
        return x < g.getMaxWidth() && y < g.getMaxHeight();
    }

    public void createGrid(GridPane g){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                Rectangle r = new Rectangle();
                r.setHeight(32);
                r.setWidth(32);
                r.setFill(Color.LIGHTBLUE);
                r.setStroke(Color.BLACK);
                g.add(r, i, j);
            }
        }
    }

    //needs to be changed to check contents of grid
    public static void colorGrid(GridPane vGrid,Grid g,boolean b){
        //loops through all rectangles
        for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++){
				Rectangle r = new Rectangle();
				r.setHeight(32);
				r.setWidth(32);

				//changes color based on status and contents
                if(g.getGridContents(i,j).isHit()){
                    if(g.getGridContents(i,j).getContainsShip()){
                        r.setFill(Color.RED);
                    }
                    else {
                        r.setFill(Color.YELLOW);
                    }
                }
				else if(g.getGridContents(i,j).getContainsShip() && b){
                    r.setFill(Color.STEELBLUE);
                }
				else {
					r.setFill(Color.LIGHTBLUE);
				}
				r.setStroke(Color.BLACK);
				vGrid.add(r, i, j);
			}
		}
	}

    public static void main(String[] args) {
        BotEasy computer = new BotEasy();
        computer.generateShips(player2Grid);

        Application.launch(args);

    }
}
