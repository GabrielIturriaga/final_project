public class Grid {
    private Point [][] grid = new Point[10][10];
    private Point point;
    public Grid(){
        makingGrid();
    }
    // This adds Point objects right now
    public void makingGrid(){
        for(int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Point(i, j);
            }
        }
    }
    public void printGrid(){
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++)
                System.out.print(grid[i][j].toString() + "\t");
            System.out.println();
        }
    }
}
