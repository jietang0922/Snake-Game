package util;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {

    private Snake snake;
    private Apple apple;

    public Panel() {
        this.snake = new Snake();
        this.apple = new Apple(this.snake.getSquares());
    }

    public void changeDirection(int direction) {
        this.snake.changeDirection(direction);
    }

    public boolean update() {
        this.snake.update();
        if(this.snake.getSquares().get(0).equals(this.apple)) {
            this.snake.grow();
            this.apple.generate(this.snake.getSquares());
        }
        if(this.snake.collision()) {
            this.snake.reset();
            return false;
        }
        this.revalidate();
        this.repaint();
        return true;
    }

    

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 0, 0));
        g.fillRect(this.apple.getX(), this.apple.getY(), Constants.SNAKE_SIZE, Constants.SNAKE_SIZE);
        for(Square square : this.snake.getSquares()) {
            g.setColor(new Color(0, 255, 255));
            g.fillRect(square.getX(), square.getY(), Constants.SNAKE_SIZE, Constants.SNAKE_SIZE);
        }
    }
}
