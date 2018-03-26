public class ShipBattleship extends Ship{
    public ShipBattleship(){
        super(4);
    }
    public String toString (){
        return ("Length: " + getLength() + " HP: " + getHP());
    }
}
