package MainGame;

import MainGame.Point;
import MainGame.Grid;

import java.util.Random;
import java.util.ArrayList;

public class FirstBot{
   Random rand = new Random();
   private ArrayList<Point> guesses;
   private ArrayList<Point> hit;
   private int hitradius = 1;
   private int counter = 0;
   private boolean boatisony = false;
   private boolean attack = false;
   private Grid grid = new Grid();
   private Point temp;
   public FirstBot(){
      guesses = new ArrayList();
      hit = new ArrayList();
   }
   public Point getGuess(){
      int x = 0,y = 0;
      int hitx, hity;
      Point guess = new Point(x,y); 
      if(guesses.size() > 0 && hit.size() > 0){
         if(guesses.get(guesses.size()-1) == hit.get(hit.size()-1)){
            hitx = hit.get(hit.size()-1).getX();
            hity = hit.get(hit.size()-1).getY();
            if(hitx < 9 && hitx > 0){
               x = rand.nextInt(hitx + 2) + hitx - 1;
            }
            else{
               x = hitx;
            }
            y = hity;
            for(int c1 = 0; c1 < guesses.size(); c1++){
               while(counter < 9 || guesses.get(c1).getX() == x && guesses.get(c1).getY() == y){
                  if(counter == 8 && hitradius < 5){
                     hitradius++;
                     counter = 0;
                  }
                  x = rand.nextInt(hitx + (1 + hitradius))  + hitx - hitradius;
                  counter++;  
               }
            }
            if(counter == 9){
               counter = 0;
               hitradius = 0;
               for(int c6 = 0; c6 < guesses.size(); c6++){
                  while(counter < 9 || guesses.get(c6).getX() == x && guesses.get(c6).getY() == y){
                     if(counter == 8 && hitradius < 9){
                        hitradius++;
                        counter = 0;
                     }
                     y = rand.nextInt(hity + (1 + hitradius))  + hity - hitradius;
                     counter++;  
                  }
               }
            }
            if(counter == 9){
               counter = 0;
               hitradius = 5;
               for(int c7 = 0; c7 < guesses.size(); c7++){
                  while(counter < 9 || guesses.get(c7).getX() == x && guesses.get(c7).getY() == y){
                     if(counter == 8 && hitradius < 9){
                        hitradius++;
                        counter = 0;
                     }
                     x = rand.nextInt(hitx + (1 + hitradius))  + hitx - hitradius;
                     counter++;  
                  }
               }
            }
            guess.setX(x);
            guess.setY(y);
            if(grid.getGridContains(x,y) == true){
               hit.add(guess);
               guesses.add(guess);
            }
            else{
               guesses.add(guess);
            }
         }
         if(guesses.size() % 4 == 0){
            for(x = rand.nextInt(11); x >= 5; x = rand.nextInt(11)){
            }
            for(y = rand.nextInt(11); y >= 5; y = rand.nextInt(11)){
            }
            guess.setX(x);
            guess.setY(y);
            for(int c2 = 0; c2 <= guesses.size()-1; c2++){
               while(x == guesses.get(c2).getX() && y == guesses.get(c2).getY()){
                  for(x = rand.nextInt(11); x >= 5; x = rand.nextInt(11)){
                  }
                  for(y = rand.nextInt(11); y >= 5; y = rand.nextInt(11)){
                  }
               }
            }
            if(grid.getGridContains(x,y) == true){
               hit.add(guess);
               guesses.add(guess);
            }
            else{
               guesses.add(guess);
            }
         }     
         else if(guesses.size() % 3 == 0){
            for(x = rand.nextInt(11); x >= 5; x = rand.nextInt(11)){
            }
            for(y = rand.nextInt(11); y < 5; y = rand.nextInt(11)){
            }
            guess.setX(x);
            guess.setY(y);
            for(int c3 = 0; c3 <= guesses.size()-1; c3++){
               while(x == guesses.get(c3).getX() && y == guesses.get(c3).getY()){
                  for(x = rand.nextInt(11); x >= 5; x = rand.nextInt(11)){
                  }
                  for(y = rand.nextInt(11); y >= 5; y = rand.nextInt(11)){
                  }
               }
            }
            if(grid.getGridContains(x,y) == true){
               hit.add(guess);
               guesses.add(guess);
            }
            else{
               guesses.add(guess);
            }
            
         }
         else if(guesses.size() % 2 == 0){
            for(x = rand.nextInt(11); x < 5; x = rand.nextInt(11)){
            }
            for(y = rand.nextInt(11); y >= 5; y = rand.nextInt(11)){
            }
            guess.setX(x);
            guess.setY(y);
            for(int c4 = 0; c4 <= guesses.size()-1; c4++){
               while(x == guesses.get(c4).getX() && y == guesses.get(c4).getY()){
                  for(x = rand.nextInt(11); x >= 5; x = rand.nextInt(11)){
                  }
                  for(y = rand.nextInt(11); y >= 5; y = rand.nextInt(11)){
                  }
               }
            }
            if(grid.getGridContains(x,y) == true){
               hit.add(guess);
               guesses.add(guess);
            }
            else{
               guesses.add(guess);
            }
   
         }
         else{
            for(x = rand.nextInt(11); x < 5; x = rand.nextInt(11)){
            }
            for(y = rand.nextInt(11); y < 5; y = rand.nextInt(11)){
            }
            guess.setX(x);
            guess.setY(y);
            for(int c5 = 0; c5 <= guesses.size()-1; c5++){
               while(x == guesses.get(c5).getX() && y == guesses.get(c5).getY()){
                  for(x = rand.nextInt(11); x >= 5; x = rand.nextInt(11)){
                  }
                  for(y = rand.nextInt(11); y >= 5; y = rand.nextInt(11)){
                  }
               }
            }
            if(grid.getGridContains(x,y) == true){
               hit.add(guess);
               guesses.add(guess);
            }
            else{
               guesses.add(guess);
            }
         }
      }
      return guess;
   }
}