package MainGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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


public class Main extends Application {

	private boolean shipsPlaced;
	private boolean playerTurn = true;
	private String textTop = "top";
	private String textBot = "bot";

    @Override
    @SuppressWarnings("Duplicates")
    public void start(Stage primaryStage) throws Exception{
        ShipBattleship battleShip = new ShipBattleship();
        ShipDestroyer destroyerShip = new ShipDestroyer();
        ShipCarrier carrierShip = new ShipCarrier();
        ShipCruiser cruiserShip = new ShipCruiser();
        ShipSubmarine submarineShip = new ShipSubmarine();
        ArrayList<Ship> shipList = new ArrayList<Ship>();

        shipList.add(battleShip);
        shipList.add(destroyerShip);
        shipList.add(carrierShip);
        shipList.add(cruiserShip);
        shipList.add(carrierShip);
        shipList.add(submarineShip);

        int width = 720;
        int height = 480;
        int THIS_IS_THE_LENGTH_OF_THE_SHIP = carrierShip.getLength();
        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setMaxWidth(10);
        grid.setMaxHeight(10);

        createGrid(grid);

//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++){
//                Rectangle r = new Rectangle();
//                r.setHeight(32);
//                r.setWidth(32);
//                r.setFill(Color.IVORY);
//                r.setStroke(Color.BLACK);
//                grid.add(r, i, j);
//            }
//        }
        Cursor cursor = new Cursor(THIS_IS_THE_LENGTH_OF_THE_SHIP);
        Cursor cursor2 = new Cursor(1);

        GridPane grid2 = new GridPane();
        grid2.setHgap(1);
        grid2.setVgap(1);
        grid2.setMaxWidth(10);
        grid2.setMaxHeight(10);

        createGrid(grid2);

        //font for text in game
		Font font = new Font("Arial",20);
		font.font("Arial",FontWeight.BOLD,20);

        Text text1 = new Text(textTop);
		text1.setFont(font);

        Text text2 = new Text(textBot);
		text2.setFont(font);

		//hbox top and bot contain game text
        HBox hboxTop = new HBox();
		hboxTop.getChildren().add(text1);
		hboxTop.setAlignment(Pos.CENTER);

        HBox hboxBot = new HBox();
        hboxBot.getChildren().add(text2);
        hboxBot.setAlignment(Pos.CENTER);

        //contains two grid objects
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(grid,grid2);

        //create anchor pane and set anchors
		AnchorPane anchorPane = new AnchorPane();

		AnchorPane.setTopAnchor(hboxTop,20.0);
		AnchorPane.setLeftAnchor(hboxTop,10.0);
		AnchorPane.setRightAnchor(hboxTop,10.0);

		AnchorPane.setLeftAnchor(hbox,10.0);
		AnchorPane.setRightAnchor(hbox,10.0);
		AnchorPane.setTopAnchor(hbox,250.0);
		AnchorPane.setBottomAnchor(hbox,250.0);

		AnchorPane.setBottomAnchor(hboxBot,20.0);
		AnchorPane.setLeftAnchor(hboxBot,10.0);
		AnchorPane.setRightAnchor(hboxBot,10.0);

		//add hboxes to anchor pane
		anchorPane.getChildren().addAll(hboxTop,hbox,hboxBot);
		//anchorPane.setPrefSize(width,height);

        Scene scene = new Scene(anchorPane,width,height);

        grid.setOnMouseMoved(e -> {
            double mx = e.getX();
            double my = e.getY();
            int mCellX = (int)mx/33;
            int mCellY = (int)my/33;
            if (inGrid(grid,mCellX,mCellY)){
                cursor.cursorToMouse(grid, mCellX, mCellY);
            }

        } );

        //need to add game logic for coloring of squares
        grid2.setOnMousePressed(e -> {
            double mX = e.getX();
            double mY = e.getY();
            int mCellX = (int)mX/33;
            int mCellY = (int)mY/33;

            if(inGrid(grid2,mCellX,mCellY)){
                cursor2.cursorToMouse(grid2,mCellX,mCellY);
            }

        });
        scene.setOnKeyPressed(e ->{
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.R)){
                cursor.changeRotation();
                //get the (x,y) cells and put them back into the cursorToMouse function to update rotation instantly
                //...absolute hack, I am so sorry
                int tempCellX = cursor.getCellX();
                int tempCellY = cursor.getCellY();
                cursor.cursorToMouse(grid, tempCellX, tempCellY);
            }
            if (key.equals(KeyCode.DOWN)){
                //THIS_IS_THE_LENGTH_OF_THE_SHIP = battleShip.getLength();
            }
        });

        primaryStage.setTitle("BattleShip");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void fillGrid(){

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
                r.setFill(Color.IVORY);
                r.setStroke(Color.BLACK);
                g.add(r, i, j);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
