package MainGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main extends Application {


    @Override
    @SuppressWarnings("Duplicates")
    public void start(Stage primaryStage) throws Exception{
        ShipBattleship battleShip = new ShipBattleship();
<<<<<<< HEAD
        ShipDestroyer DestroyerShip = new ShipDestroyer();
        ShipCarrier CarrierShip = new ShipCarrier();
        ShipCruiser CruiserShip = new ShipCruiser();
        ShipSubmarine SubmarineShip = new ShipSubmarine();
        Stack<Ship> shipStack = new Stack<>();
        shipStack.push(battleShip);
        shipStack.push(DestroyerShip);
        shipStack.push(CarrierShip);
        shipStack.push(CruiserShip);
        shipStack.push(CarrierShip);
        shipStack.push(SubmarineShip);
        int width = 640;
        int height = 480;
        int shipLength = shipStack.get(0).getLength();
=======
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
>>>>>>> ffee895838c0e75994a300a936950d67c418cf47
        GridPane grid = new GridPane();
        Grid player1Grid = new Grid();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setMaxWidth(10);
        grid.setMaxHeight(10);
<<<<<<< HEAD

        Cursor cursor = new Cursor(shipLength);
        Scene scene = new Scene(grid, width,height);
        scene.setOnMouseMoved(e -> {
=======

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

        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(grid,grid2);

        Scene scene = new Scene(hbox,width,height);

        grid.setOnMouseMoved(e -> {
>>>>>>> ffee895838c0e75994a300a936950d67c418cf47
            double mx = e.getX();
            double my = e.getY();
            int mCellX = (int)mx/33;
            int mCellY = (int)my/33;
            if (inGrid(grid,mCellX,mCellY)){
                cursor.cursorToMouse(grid, mCellX, mCellY);
            }

        } );
<<<<<<< HEAD
        scene.setOnMousePressed(e -> {
            if (shipStack.size() > 1 && cursor.checkForShip(player1Grid)){
                Ship currentShip = shipStack.peek();
                cursor.placeShip(grid, player1Grid, currentShip);

                //Removing the placed ship from stack, setting new cursor length
                shipStack.pop();
                int newLength = shipStack.peek().getLength();
                cursor.setCellLength(newLength);
            }
        });
=======

        grid2.setOnMousePressed(e -> {
            double mX = e.getX();
            double mY = e.getY();
            int mCellX = (int)mX/33;
            int mCellY = (int)mY/33;

            if(inGrid(grid2,mCellX,mCellY)){
                cursor2.cursorToMouse(grid2,mCellX,mCellY);
            }

        });

>>>>>>> ffee895838c0e75994a300a936950d67c418cf47
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
