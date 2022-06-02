package util;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class Frame extends JFrame implements KeyListener {

    private Panel panel;

    public Frame(String title) {
        super(title);
        this.addKeyListener(this);
        this.setSize(Constants.WINDOW_SIZE + Constants.WINDOW_X_OFFSET, Constants.WINDOW_SIZE + Constants.WINDOW_Y_OFFSET);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        this.panel = new Panel();
        this.panel.run();
        this.add(this.panel);
    }

    public void start() {
        this.panel.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 27) {
            this.dispose();
        }
        else if (key >= 37 && key <= 40) {
            this.panel.changeDirection(key - 37);
        }
            
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
