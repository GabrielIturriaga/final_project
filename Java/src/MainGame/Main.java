package MainGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import java.util.ArrayList;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        ShipBattleship battleShip = new ShipBattleship();
        ShipDestroyer DestroyerShip = new ShipDestroyer();
        ShipCarrier CarrierShip = new ShipCarrier();
        ShipCruiser CruiserShip = new ShipCruiser();
        ShipSubmarine SubmarineShip = new ShipSubmarine();
        ArrayList<Ship> ShipList = new ArrayList<Ship>();
        ShipList.add(battleShip);
        ShipList.add(DestroyerShip);
        ShipList.add(CarrierShip);
        ShipList.add(CruiserShip);
        ShipList.add(CarrierShip);
        ShipList.add(SubmarineShip);
        int width = 640;
        int height = 480;
        int THIS_IS_THE_LENGTH_OF_THE_SHIP = CarrierShip.getLength();
        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setMaxWidth(10);
        grid.setMaxHeight(10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                Rectangle r = new Rectangle();
                r.setHeight(32);
                r.setWidth(32);
                r.setFill(Color.IVORY);
                r.setStroke(Color.BLACK);
                grid.add(r, i, j);
            }
        }
        Cursor cursor = new Cursor(THIS_IS_THE_LENGTH_OF_THE_SHIP);
        Scene scene = new Scene(grid, width,height);
        scene.setOnMouseMoved(e -> {
            double mx = e.getX();
            double my = e.getY();
            int mCellX = (int)mx/33;
            int mCellY = (int)my/33;
            if (mCellX < grid.getMaxWidth() && mCellY < grid.getMaxHeight()){
                cursor.cursorToMouse(grid, mCellX, mCellY);
            }

        } );
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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
