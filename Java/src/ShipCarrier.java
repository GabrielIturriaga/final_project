public class ShipCarrier extends Ship {
    public ShipCarrier(){
        super(5);
    }
    public String toString (){
        return ("Length: " + super.getLength() + " HP: " + super.getHP());
    }
}
