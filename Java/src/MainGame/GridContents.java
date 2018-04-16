package MainGame;

public class GridContents {
    private Point point;
    private boolean containsShip;
    private Ship ship; // store which this point belongs to

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
