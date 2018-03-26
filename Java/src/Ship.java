public abstract class Ship {
    // Attributes
    private int length;
    private int HP;

    //bring in things for the ship
    public Ship(int length){
        // Assign to the attribute
        this.length = length;
        this.HP = length;
    }
    // Setters
    public void setLength(int length){this.length = length;}
    public void setHP(int HP){this.HP = HP;}
    //public void setPoint(Point point){}
    // Getters
    // Will change the void to whatever the return value is supposed to be
    public int getLength(){return length;}
    public int getHP(){return HP;}
    //public Point getPoint(){return Point;}
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
