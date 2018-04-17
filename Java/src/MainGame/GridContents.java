package MainGame;

public class GridContents {
    private Point point;
    private boolean containsShip;
    private Ship ship; // store which this point belongs to
	private boolean hit = false; // if point in grid has been guessed

    public GridContents(Point p){
        point = p;
        containsShip = false;
    }

    public Point getPoint() {
        return point;
    }
    // the value if the object has a ship on it.
    public boolean getContainsShip() {
        return containsShip;
    }

    public void setShip(Ship ship){ this.ship = ship; }
    public Ship getShip(){ return this.ship; }

    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    //changes hit status to true
    public void hit(){ this.hit = true; }
    public boolean isHit(){ return this.hit; }

    public String toString(){
        return ("(" + point.getX() + ", " + point.getY() + ") " + containsShip + "\t");
    }

}
