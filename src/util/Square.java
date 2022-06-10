package util;

import java.awt.Color;

public class Square {
    private int x, y;
    private Color color;

    public Square() {
        
    }

    public Square(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean equals(Object other) {
        return this.x == ((Square) other).getX() && this.y == ((Square) other).getY();
    }
}
