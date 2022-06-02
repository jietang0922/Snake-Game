package util;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class Panel extends JPanel {

    private Snake snake;
    private Apple apple;
    private SwingWorker<Object, Object> sw;

    public Panel() {

        this.snake = new Snake();
        this.apple = new Apple(this.snake.getSquares());

        this.sw = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    update();
                    Thread.sleep(1000 / Constants.FRAME_RATE);
                }
            }
        };
    }

    public void changeDirection(int direction) {
        this.snake.changeDirection(direction);
    }

    public void update() {
        this.snake.update();
        if(this.snake.getSquares().get(0).equals(this.apple)) {
            this.snake.grow();
            this.apple.generate(this.snake.getSquares());
        }
        this.revalidate();
        this.repaint();
    }

    public void run() {
        this.sw.execute();
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
