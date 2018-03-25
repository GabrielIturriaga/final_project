import java.util.ArrayList;
import java.util.Random;

public class BotEasy {

	//stores points already guessed
	private ArrayList<Point> guess;

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
}
