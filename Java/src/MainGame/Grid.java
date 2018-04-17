package MainGame;

import java.util.ArrayList;

public class Grid {
    private GridContents[][] grid = new GridContents[10][10];
    public Grid(){
        makeGrid();
    }
    // This adds MainGame.Point objects right now
    public void makeGrid(){
        for(int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[j][i] = new GridContents(new Point(j, i));
            }
        }
    }

    // This "adds" the ship point to the GridContents
    public void addToGrid(Point p){
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                if(p.getY() == (grid[i][j].getPoint().getY()) && p.getX() == (grid[i][j].getPoint().getX()))
                    grid[i][j].setContainsShip(true);
            }
        }
    }

    public GridContents getGridContents(int x, int y){
        return grid[x][y];
    }

    public void printGrid(){
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++)
                System.out.printf("%-30s", grid[i][j].toString());
            System.out.println();
        }
    }

    //checks if ship is in point
    public boolean check(Point p){
    	return getGridContents(p.getX(),p.getY()).getContainsShip();
	}
}
