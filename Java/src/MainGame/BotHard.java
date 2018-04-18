package MainGame;

import java.util.ArrayList;
import java.util.Random;

public class BotHard {

	//stores points already guessed
	private ArrayList<Point> guess;
	private Grid grid;
	private boolean lastHit = false;
	private Point lastPoint;

	public BotHard(){
		guess = new ArrayList<>();
	}

	public Point getGuess(){
		Point guessPoint;
		int x = 20;
		int y = 20;
		Random random = new Random();

		if(lastHit == true){
			int r = random.nextInt(2);
			if(r == 0){
				if(newGuess(lastPoint.getX()+1,lastPoint.getY())&&lastPoint.getX()+1<10){
					x = lastPoint.getX()+1;
					y = lastPoint.getY();
				}
				else if(newGuess(lastPoint.getX()-1,lastPoint.getY())){
					x = lastPoint.getX()-1;
					y = lastPoint.getY();
				}
			}
			else {
				if(newGuess(lastPoint.getX(),lastPoint.getY()+1)&&lastPoint.getY()+1<10){
					x = lastPoint.getX();
					y = lastPoint.getY()+1;
				}
				else if(newGuess(lastPoint.getX(),lastPoint.getY()-1)){
					x = lastPoint.getX();
					y = lastPoint.getY()-1;
				}
			}
		}
		else {
			do {
				x = random.nextInt(10);
				y = random.nextInt(10);
			} while (!newGuess(x, y));
		}

		if(x==20 && y==20){
			do {
				x = random.nextInt(10);
				y = random.nextInt(10);
			} while (!newGuess(x, y));
		}

		guessPoint = new Point(x,y);
		lastPoint = guessPoint;
		this.guess.add(guessPoint);

		return guessPoint;
	}

	public void setLastHit(boolean b){
		this.lastHit = b;
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
