public abstract class Ship{
    // Attributes

    private int length;
    private int HP;
    private Point point ;
    private Point [] points ;
    //bring in things for the ship
    public Ship(int length){
        // Assign to the attribute
        this.length = length;
        this.HP = length;
        point = new Point(1,1 );
        points = new Point[length];
        addPoints();
    }
    // Setters
    public void setLength(int length){this.length = length;}

    public void setHP(int HP){this.HP = HP;}

    // Getters
    // Will change the void to whatever the return value is supposed to be

    public int getLength(){return length;}

    public int getHP(){return HP;}

    public int getShipX() {return point.getX();}

    public void setShipX(int x) {point.setX(x);}

    public int getShipY() {return point.getY();}

    public void setShipY(int y) {point.setY(y);}

    public void addPoints(){
        for(int i = 0; i < points.length; i++){
            points[i] = point;
        }
    }
    // Just Prints out the Coors for the Ship
    public String PointsToString(){
        String pointArray = "Point:";
        for(int i = 0; i < points.length; i++){

            pointArray += " (" + getShipX() + ", " + getShipY() + ") ";
        }
        return (pointArray);
    }
    // Just Prints out the length and the HP
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
