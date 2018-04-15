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


public class Main extends Application {


    @Override
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
            double mx = e.getX();
            double my = e.getY();
            int mCellX = (int)mx/33;
            int mCellY = (int)my/33;
            if (mCellX < grid.getMaxWidth() && mCellY < grid.getMaxHeight()){
                cursor.cursorToMouse(grid, mCellX, mCellY);
            }

        } );

        grid.setOnKeyPressed(e ->{
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
