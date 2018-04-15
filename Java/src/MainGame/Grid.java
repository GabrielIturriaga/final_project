package MainGame;

public class Grid {
    private GridContents[][] grid = new GridContents[10][10];
    private Point point;
    private GridContents go;
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

    public void printGrid(){
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++)
                System.out.print(grid[j][i].toString() + "\t");
            System.out.println();
        }
    }
}
