public class ShipSubmarine extends Ship{
    public ShipSubmarine (){
        super(3);
    }
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
