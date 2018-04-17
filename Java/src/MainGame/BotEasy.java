package MainGame;

import MainGame.Grid;
import MainGame.Point;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class BotEasy {

	//stores points already guessed
	private ArrayList<Point> guess;
	private Grid grid;

	public BotEasy(){
		guess = new ArrayList<>();
	}

	public Point getGuess(){
		Point guessPoint;
		int x, y;
		Random random = new Random();

		do {
			x = random.nextInt(11);
			y = random.nextInt(11);
		}while(!newGuess(x,y));

		guessPoint = new Point(x,y);
		this.guess.add(guessPoint);

		return guessPoint;
	}

	public boolean newGuess(int x, int y){
		for (int i = 0; i < guess.size(); i++) {
			if(guess.get(i).getX() == x && guess.get(i).getY() == y){
				return false;
			}
		}
		return true;
	}

	public void placeShips(){
		//place ships randomly
		//assuming no overlap
	}

	public void generateShips(Grid gameGrid){
		Stack<Ship> shipStack = new Stack();

		ShipBattleship batShip = new ShipBattleship();
		ShipCarrier carShip = new ShipCarrier();
		ShipCruiser cruShip = new ShipCruiser();
		ShipDestroyer desShip = new ShipDestroyer();
		ShipSubmarine subShip = new ShipSubmarine();

		shipStack.push(batShip);
		shipStack.push(carShip);
		shipStack.push(desShip);
		shipStack.push(cruShip);
		shipStack.push(subShip);

		int stackSize = shipStack.size();

		Random rand1 = new Random();
		Random rand2 = new Random();
		Random rand3 = new Random();

		Ship currentShip = shipStack.peek();

		for (int i = 0; i < stackSize; i++){
			boolean placingShip = true;
			while(placingShip){
				int shipLength = currentShip.getLength();

				int shipX = rand1.nextInt(11);
				int shipY = rand2.nextInt(11);
				int rotation = rand3.nextInt(2); //0 = horizontal, 1 = vertical

				if (canPlaceShip(gameGrid, shipLength, shipX, shipY, rotation, currentShip)){
					placingShip = false;
				}

			}
		}
	}

	public boolean canPlaceShip(Grid gameGrid, int shipLength, int x, int y, int rotation, Ship currentShip){
		boolean canPlace = true;
		boolean nowContainsShips = false;
		//checks every tile for a possible ship
		for (int i = 0; i < shipLength; i++){
			if (rotation == 0) {
				GridContents gridData = gameGrid.getGridContents(i + (x - shipLength / 2),y);
				if (gridData.getcontainsShip()){
					canPlace = false;
				}
			}
			if (rotation == 1) {
				GridContents gridData = gameGrid.getGridContents(x, i + (y - shipLength / 2));
				if (gridData.getcontainsShip()){
					canPlace = false;
				}
			}
		}
		//only change tiles to contain a ship, if none of them had a former ship
		for (int i = 0; i < shipLength; i++){
			if (rotation == 0) {
				GridContents gridData = gameGrid.getGridContents(i + (x - shipLength / 2),y);
				gridData.setContainsShip(true);
			}
			if (rotation == 1) {
				GridContents gridData = gameGrid.getGridContents(x, i + (y - shipLength / 2));
				gridData.setContainsShip(true);
			}
		}
		return canPlace;
	}
}
