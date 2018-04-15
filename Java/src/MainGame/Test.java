package MainGame;

import java.util.ArrayList;
import java.util.Scanner;
public class Test {
    public static void main (String [] args){
        Scanner kb = new Scanner(System.in);
        // Creates the grid
        Grid grid = new Grid();
        // Creates the ships
        ShipBattleship battleShip = new ShipBattleship();
        ShipDestroyer DestroyerShip = new ShipDestroyer();
        ShipCarrier CarrierShip = new ShipCarrier();
        ShipCruiser CruiserShip = new ShipCruiser();
        ShipSubmarine SubmarineShip = new ShipSubmarine();
        ArrayList<Ship> ShipList = new ArrayList<>();
        // Adds the ships to an array
        ShipList.add(battleShip);
        ShipList.add(DestroyerShip);
        ShipList.add(CarrierShip);
        ShipList.add(CruiserShip);
        ShipList.add(CarrierShip);
        ShipList.add(SubmarineShip);
        //System.out.println(ShipList);
        System.out.println();
        grid.printGrid();
        // Places the ships on the board
        for (int i = 0; i < ShipList.size(); i++) {
            // This string is used to make their letter guess into a number.
            String compare = "abcdefghij";
            int x;
            System.out.print("What top left Coordinate would you like to place the " + ShipList.get(i).getName() + "  Ex: B 3 : ");
            String sx = kb.next();
            sx = sx.toLowerCase();
            x = compare.indexOf(sx.charAt(0));
            int y = kb.nextInt() - 1;
            //System.out.println(x);
            //System.out.println(y);
            System.out.print("Would you like place the ship Horizontal or Vertical (H or V): ");
            String direction = kb.next();
            // Adds the points to the ships and adds it to the grid
            ShipList.get(i).addPoints(new Point(x, y), direction, grid);
            //System.out.println(direction);
            //System.out.println(ShipList.get(i).getPoints());
            grid.printGrid();

        }
    }
}