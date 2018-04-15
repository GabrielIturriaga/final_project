package MainGame;

import MainGame.Point;

public class Grid {
    private GridObject [][] grid = new GridObject[10][10];
    private Point point;
    private GridObject go;
    public Grid(){
        makingGrid();
    }
    // This adds MainGame.Point objects right now
    public void makingGrid(){
        for(int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[j][i] = new GridObject(new Point(j, i));
            }
        }
    }
    // This "adds" the ship point to the GridObject
    public void addsToGrid(Point p){
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                if(p.getY() == (grid[i][j].getPoint().getY()) && p.getX() == (grid[i][j].getPoint().getX()))
                    grid[i][j].setHasAShip(true);
            }
        }
    }

    public void printGrid(){
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++)
                System.out.print(grid[j][i].toString() + "\t");
            System.out.println();
        }
    }
}
