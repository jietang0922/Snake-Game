package util;

import java.awt.Color;

public class Square {
    private int x, y;
    private Color color;

    public Square() {
        
    }

    public Square(int x, int y, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.color = new Color(r, g, b);
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

    public boolean equals(Square other) {
        return this.x == other.x && this.y == other.y;
    }
}
