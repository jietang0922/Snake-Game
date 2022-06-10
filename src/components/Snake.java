package components;

import java.awt.Color;

import java.util.ArrayList;

import util.Constants;
import util.JSONReader;
import util.Square;

public class Snake {

    private int direction;
    private ArrayList<Color> colors;
    private String skin;
    private int colorIndex;
    private boolean canChangeDirection;
    private ArrayList<Square> squares;

    public Snake() {
        this.direction = 0;
        this.colorIndex = 0;
        this.skin = "default";
        this.colors = new ArrayList<>();
        this.canChangeDirection = false;
        this.squares = new ArrayList<>();
        this.start();
    }

    public void start() {
        this.direction = 0;
        this.colorIndex = 0;
        this.colors = JSONReader.getSkin("default");
        int x = Constants.WINDOW_SIZE / 2;
        Color color = this.colors.get(this.colorIndex);
        for (int i = 0; i < Constants.INITIAL_SNAKE_LENGTH; i++) {
            this.squares.add(new Square(x, Constants.WINDOW_SIZE / 2, this.colors.get(this.colorIndex)));
            x += Constants.SNAKE_SIZE;
            this.colorIndex = (this.colorIndex + 1) % this.colors.size();
        }
    }

    public void reset() {
        this.squares.clear();
        this.start();
    }

    public void changeDirection(int direction) {
        if (this.canChangeDirection && this.direction != direction) {
            this.direction = (direction + this.direction) % 2 == 0 ? this.direction : direction;
            this.canChangeDirection = false;
        }
    }

    public void changeSkin(String name) {
        if (!name.equals(this.skin)) {
            this.skin = name;
            this.colors = JSONReader.getSkin(name);
            this.colorIndex = 0;
            for (Square square : this.squares) {
                Color color = this.colors.get(this.colorIndex);
                square.setColor(color);
                this.colorIndex = (this.colorIndex + 1) % this.colors.size();
            }
        }
    }

    public void update() {
        for (int i = this.squares.size() - 1; i >= 1; i--) {
            this.squares.get(i).setX(this.squares.get(i - 1).getX());
            this.squares.get(i).setY(this.squares.get(i - 1).getY());
        }
        Square lead = this.squares.get(0);
        switch (this.direction) {
            case 0: // left
                lead.setX((lead.getX() - Constants.SNAKE_SIZE + Constants.WINDOW_SIZE) % Constants.WINDOW_SIZE);
                break;
            case 1: // up
                lead.setY((lead.getY() - Constants.SNAKE_SIZE + Constants.WINDOW_SIZE) % Constants.WINDOW_SIZE);
                break;
            case 2: // right
                lead.setX((lead.getX() + Constants.SNAKE_SIZE + Constants.WINDOW_SIZE) % Constants.WINDOW_SIZE);
                break;
            case 3: // down
                lead.setY((lead.getY() + Constants.SNAKE_SIZE + Constants.WINDOW_SIZE) % Constants.WINDOW_SIZE);
                break;
        }
        this.canChangeDirection = true;
    }

    public boolean collision() {
        for (int i = 4; i < this.squares.size(); i++) {
            if (this.squares.get(0).equals(this.squares.get(i)))
                return true;
        }
        return false;
    }

    public void grow() {
        int x = 2 * this.squares.get(this.squares.size() - 1).getX() - this.squares.get(this.squares.size() - 2).getX();
        int y = 2 * this.squares.get(this.squares.size() - 1).getY() - this.squares.get(this.squares.size() - 2).getY();
        this.squares.add(new Square(x, y, this.colors.get(this.colorIndex)));
        this.colorIndex = (this.colorIndex + 1) % this.colors.size();
    }

    public ArrayList<Square> getSquares() {
        return this.squares;
    }
}
