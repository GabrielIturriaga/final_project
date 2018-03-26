public class ShipCruiser extends Ship{
    public ShipCruiser(){
        super(3);
    }
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
