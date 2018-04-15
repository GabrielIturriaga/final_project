package MainGame;

public class GridContents {
    private Point point;
    private boolean containsShip;
    private String ship; // store which ship occupies the the point in the grid

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

    public void setShip(String ship){ this.ship = ship; }
    public String getShip(){ return this.ship; }

    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    public String toString(){
        return ("(" + point.getX() + ", " + point.getY() + ") " + containsShip + "\t");
    }

}
