package MainGame;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Cursor{
    private int cellX, cellY, cellLength;
    private ArrayList<CursorRect> rectList = new ArrayList<CursorRect>();
    private ArrayList<PlacedRect> placedRectList = new ArrayList<PlacedRect>();//storage for the placed rects
    private boolean isHorizontal;

    public Cursor(int cellLength){
        for (int i = 0; i < cellLength; i ++){
            placedRectList.add(new PlacedRect());
        }
        this.cellLength = cellLength; // TOTAL AMOUNT OF CELLS (SHIP SIZE)
        generateCursorRects(); //creating rectangles for placement preview
        this.isHorizontal = false;
    }

    //moves cursor (selected ship) around as the mouse moves on the grid
    public void cursorToMouse(GridPane grid,int mCellX,int mCellY){

        /** temporary workaround for small brain limitations
         * this garbage will be fixed eventually. Fixes
         odd and even ship lengths working differently.*/
        int extra;
        if (cellLength % 2 == 0){ extra = 1; } else {extra = 0;}

        /**needs to be turned into a smaller function that takes in the rotation,
         * as both are very similar but with a few opposite variables.
         */
        if (isHorizontal == true){
            if (mCellY < grid.getMaxHeight()){
                //handling edge cases for smoother mouse movement
                if (getNewMaxCell(mCellX) >= grid.getMaxWidth()){
                    cellX = (int)grid.getMaxWidth() - cellLength/2 - 1 + extra;
                }
                else if (getNewMinCell(mCellX) < 0){
                    cellX = cellLength / 2;
                }
                else{
                    cellX = mCellX;
                }
                cellY = mCellY;
                for (int i = 0; i < cellLength; i++){
                    grid.getChildren().remove(rectList.get(i));
                    grid.add(rectList.get(i), i + (cellX - cellLength / 2), cellY);
                }
            }
        }
        else if(isHorizontal == false){
            if (mCellX < grid.getMaxHeight()){
                //handling edge cases for smoother mouse movement
                if (getNewMaxCell(mCellY) >= grid.getMaxHeight()){
                    cellY = (int)grid.getMaxHeight() - cellLength/2 - 1 + extra;
                }
                else if (getNewMinCell(mCellY) < 0){
                    cellY = cellLength / 2;
                }
                else{
                    cellY = mCellY;
                }
                cellX = mCellX;
                for (int i = 0; i < cellLength; i++){
                    grid.getChildren().remove(rectList.get(i));
                    grid.add(rectList.get(i), cellX, i + (cellY - cellLength / 2));
                }
            }
        }
    }
    //GridPane is javafx visuals, gameGrid is the backend grid for the game
    public void placeShip(GridPane grid, Grid gameGrid, Ship currentShip){
        for (int i = 0; i < cellLength; i++){
            grid.getChildren().remove(rectList.get(i));
            if (isHorizontal == true) {
                grid.add(placedRectList.get(i), i + (cellX - cellLength / 2), cellY);
                GridContents gridData = gameGrid.getGridContents(i + (cellX - cellLength / 2), cellY);
                gridData.setContainsShip(true);
                gridData.setShip(currentShip);
            }
            if (isHorizontal == false) {
                grid.add(placedRectList.get(i), cellX, i + (cellY - cellLength / 2));
                GridContents gridData = gameGrid.getGridContents(cellX, i + (cellY - cellLength / 2));
                gridData.setContainsShip(true);
                gridData.setShip(currentShip);
            }
        }
    }

    //used to update the preview rectangles for cursor
    public void generateCursorRects(){
        rectList = new ArrayList<>();
        for (int i = 0; i < cellLength; i ++){
            rectList.add(new CursorRect()); //RECTANGLES REPRESENTING THE CURSOR
        }
    }
    //updates the list of placedrect objects, call this if ship length changes
    public void generatePlacedRects(){
        placedRectList = new ArrayList<>();
        for (int i = 0; i < cellLength; i ++){
            placedRectList.add(new PlacedRect());
        }
    }
    public boolean checkForShip(Grid gameGrid){
        boolean canPlace = true;
        for (int i = 0; i < cellLength; i++){
            if (isHorizontal == true) {
                GridContents gridData = gameGrid.getGridContents(i + (cellX - cellLength / 2),cellY);
                if (gridData.getcontainsShip()){
                    canPlace = false;
                }
            }
            if (isHorizontal == false) {
                GridContents gridData = gameGrid.getGridContents(cellX, i + (cellY - cellLength / 2));
                if (gridData.getcontainsShip()){
                    canPlace = false;
                }
            }
        }
        return canPlace;
    }
    public void changeRotation(){
        isHorizontal = !isHorizontal;
    }
    public int getNewMaxCell(int newCell){
        return newCell + cellLength/2;
    }
    public int getNewMinCell(int newCell){
        return newCell - cellLength/2;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    //updating cell length, used when changing ships
    public void setCellLength(int cellLength) {
        this.cellLength = cellLength;
        generateCursorRects();
        generatePlacedRects();
    }
}
