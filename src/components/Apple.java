package components;

import java.util.ArrayList;

import util.Constants;
import util.Square;

public class Apple extends Square {

    public Apple(ArrayList<Square> snake) {
        this.generate(snake);
    }

    public void generate(ArrayList<Square> snake) {
        boolean eaten = false;
        int x, y;
        do {
            x = (int) Math.floor(Constants.WINDOW_SIZE / Constants.SNAKE_SIZE * Math.random()) * Constants.SNAKE_SIZE;
            y = (int) Math.floor(Constants.WINDOW_SIZE / Constants.SNAKE_SIZE * Math.random()) * Constants.SNAKE_SIZE;
            for(Square square : snake) {
                if(x == square.getX() && y == square.getY()) {
                    eaten = true;
                    break;
                }
            }
        } while(eaten);

        this.setX(x);
        this.setY(y);
    }

   
}
