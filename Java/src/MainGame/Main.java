package MainGame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static String textTop = "top";
    private static String textBot = "bot";
	private static boolean shipsPlaced = false; // should be by default false
	private static boolean playerTurn = true;
	private static boolean difficulty = true;
	private static Grid player1Grid = new Grid();
	private static Grid player2Grid = new Grid();
	private static Grid computerGrid = new Grid();

    public BotEasy bot = new BotEasy();

	//needed to change to static to access from main method
	private static GridPane grid;

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

        int shipLength = shipStack.get(0).getLength();

        //size of window
        int width = 720;
        int height = 480;

        Cursor cursor = new Cursor(shipLength);
        Cursor cursor2 = new Cursor(1);

        grid = new GridPane();
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

        Button button1 = new Button("EASY");

        Button button2 = new Button("HARD");

        button1.setTranslateX(360);
        button1.setTranslateY(240);

        Group root1 = new Group();

        Scene scene1 = new Scene(root1, 720, 480, Color.LIGHTBLUE);



        root1.getChildren().addAll(button1, button2);

        Scene startScene ;
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
            if (shipStack.size() > 0 && cursor.checkForShip(player1Grid) && !shipsPlaced){
                Ship currentShip = shipStack.peek();
                cursor.placeShip(grid, player1Grid, currentShip);


                    //Removing the placed ship from stack, setting new cursor length
                    //shipStack.pop();
                    int newLength = shipStack.pop().getLength();
                    cursor.setCellLength(newLength);

            }

            if(shipStack.size() == 0){
        	    shipsPlaced = true;
        	    cursor.setCellLength(0);
            }
        });

        // Button that goes to the normal game
        button1.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.show();
            difficulty = true;
        });

        button2.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.show();
            difficulty = false;
        });
        // back button even to go back to the main menu
        backButton.setOnAction(e -> {
            start(primaryStage);
            for(int i=0;i<10;i++) {
                for(int j=0;j<10;j++) {
                    player1Grid.getGridContents(j, i).setContainsShip(false);
                }
            }
            shipsPlaced = false; // should be by default false
            playerTurn = true;
        });

        grid2.setOnMousePressed(e -> {
        	//if not the players turn it doesn't allow interaction
        	if(!playerTurn){
        		return;
			}
            double mX = e.getX();
            double mY = e.getY();
            int mCellX = (int)mX/33;
            int mCellY = (int)mY/33;

            if(inGrid(grid2,mCellX,mCellY)){
                cursor2.cursorToMouse(grid2,mCellX,mCellY);
            }

            player2Grid.getGridContents(mCellX,mCellY).hit();

            colorGrid(grid2,player2Grid);

            playerTurn = false;
            computerGuess(player1Grid,grid);
        });

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

        primaryStage.setTitle("BattleShip");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private void computerGuess(Grid g,GridPane vGrid){
        //time delay
        Point p;
        p = bot.getGuess();
        g.getGridContents(p.getX(),p.getY()).hit();
        colorGrid(vGrid,g);
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
    public static void colorGrid(GridPane vGrid,Grid g){
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++){
				Rectangle r = new Rectangle();
				r.setHeight(32);
				r.setWidth(32);
                if(g.getGridContents(i,j).isHit()){
                    if(g.getGridContents(i,j).getContainsShip()){
                        r.setFill(Color.RED);
                    }
                    else {
                        r.setFill(Color.WHITE);
                    }
                }
				else if(g.getGridContents(i,j).getContainsShip()){
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
        player2Grid.printGrid();

        Application.launch(args);



        Point guess;
        boolean result;

        //this possibly will work
//        while(true){
//        	if(!playerTurn){
//        		guess = computer.getGuess();
//        		//check location on grid
//				result = computerGrid.check(guess);
//
//				if(result){
//					computerGrid.getGridContents(guess.getX(),guess.getY()).hit();
//				}
//
//				colorGrid(grid,guess);
//			}
//		}
    }
}
