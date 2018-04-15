package MainGame;

public class GridObject {
    private Point point;
    private boolean hasAShip;
    public GridObject (Point p){
        point = p;
        hasAShip = false;
    }

    public Point getPoint() {
        return point;
    }
    // the value if the object has a ship on it.
    public boolean getisHasAShip() {
        return hasAShip;
    }

    public void setHasAShip(boolean hasAShip) {
        this.hasAShip = hasAShip;
    }

    public String toString(){
        return ("(" + point.getX() + ", " + point.getY() + ") " + hasAShip + "\t");
    }

}
