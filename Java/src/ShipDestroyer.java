public class ShipDestroyer extends Ship {
    public ShipDestroyer(){
        super(2);
    }
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
