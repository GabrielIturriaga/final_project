package MainGame;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Cursor{
    private int cellX, cellY, cellLength;
    private ArrayList<CursorRect> rectList = new ArrayList<CursorRect>();
    private boolean isHorizontal;

    public Cursor(int cellLength){
        for (int i = 0; i < cellLength; i ++){
            rectList.add(new CursorRect()); //RECTANGLES REPRESENTING THE CURSOR
        }
        this.cellX = cellX; //cellX IS THE MIDDLE CELL'S X COORD
        this.cellY = cellY; //cellY IS THE MIDDLE CELL'S Y COORD
        this.cellLength = cellLength; // TOTAL AMOUNT OF CELLS (SHIP SIZE)
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
}
