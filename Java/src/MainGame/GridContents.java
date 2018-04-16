package MainGame;

public class GridContents {
    private Point point;
    private boolean containsShip;
<<<<<<< HEAD
    private Ship ship; // store which this point belongs to
=======
    private Ship ship; // store which ship occupies the the point in the grid
>>>>>>> ffee895838c0e75994a300a936950d67c418cf47

    public GridContents(Point p){
        point = p;
        containsShip = false;
    }

    public Point getPoint() {
        return point;
    }
    // the value if the object has a ship on it.
    public boolean getcontainsShip() {
        return containsShip;
    }

    public void setShip(Ship ship){ this.ship = ship; }
    public Ship getShip(){ return this.ship; }

    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    public String toString(){
        return ("(" + point.getX() + ", " + point.getY() + ") " + containsShip + "\t");
    }

}
