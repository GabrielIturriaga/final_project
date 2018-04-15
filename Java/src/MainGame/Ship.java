package MainGame;
import java.util.ArrayList;
public abstract class Ship{
    // Attributes

    private int length;
    private int HP;
    private String name;
    //private Point point ;
    private ArrayList <Point> points ;
    //bring in things for the ship
    public Ship(int length, String name){
        // Assign to the attribute
        this.name = name;
        this.length = length;
        this.HP = length;
        //point = new Point(1,1);
        points = new ArrayList<>();
        //addPoints();
    }
    // Setters
    public void setLength(int length){this.length = length;}

    public String getName() {return name;}

    public void setHP(int HP){this.HP = HP;}

    // Getters
    // Will change the void to whatever the return value is supposed to be

    public int getLength(){return length;}

    public int getHP(){return HP;}

    public String setName(){return name;}

    //public int getShipX() {return point.getX();}

    //public void setShipX(int x) {point.setX(x);}

    //public int getShipY() {return point.getY();}

    //public void setShipY(int y) {point.setY(y);}

    public ArrayList getPoints () {return points;}
    // Adds the points of the ship to an arraylist
    public void addPoints(Point p, String direction, Grid grid){
        for(int i = 0; i < getLength(); i++) {
            if (direction.equals("H")) {
                // Adds the points to the array based on the direction put in.
                Point nPoint = new Point(p.getX() + i, p.getY());
                points.add(nPoint);
                // Adds to the grid
                grid.addsToGrid(nPoint);
            }
            else {
                Point nPoint = new Point(p.getX(), p.getY() + i);
                points.add(nPoint);
                grid.addsToGrid(nPoint);
            }
        }
    }
    // Just Prints out the Coors for the MainGame.Ship
    /*public String PointsToString(){
        String pointArray = "MainGame.Point:";
        for(int i = 0; i < points.size(); i++){

            pointArray += " (" + getShipX() + ", " + getShipY() + ") ";
        }
        return (pointArray);
    }*/
    // Just Prints out the length and the HP
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
