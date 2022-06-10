import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.json.simple.JSONArray;

import util.Constants;
import util.JSONReader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {

    private Panel panel;
    private final String endMessage = "Game Over";
    private JOptionPane pane;
    private JDialog dialog;
    private boolean ultimateWeapon;
    private String[] options = { "Restart", "Quit" };
    private String[] secretMessages;
    private SwingWorker<Object, Object> sw;

    public Frame(String title) {
        super(title);
        this.addKeyListener(this);
        this.setSize(Constants.WINDOW_SIZE + Constants.WINDOW_X_OFFSET,
                Constants.WINDOW_SIZE + Constants.WINDOW_Y_OFFSET);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.panel = new Panel();
        this.add(this.panel);
        this.pane = new JOptionPane(this.endMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null,
                this.options, null);
        this.dialog = pane.createDialog("GAME OVER");
        this.dialog.setFocusable(true);

        this.secretMessages = JSONReader.getMessages();

        this.sw = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    update();
                    Thread.sleep(1000 / Constants.FRAME_RATE);
                }
            }
        };

        this.ultimateWeapon = false;
    }

    public void start() {
        this.sw.execute();
    }

    public void end(String message, int annoyCount) {
        if (this.ultimateWeapon) {
            this.dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }

        this.pane.setMessage(message);
        this.dialog.setVisible(true);

        Object selectedValue = pane.getValue();
        Integer choice = null;

        // ADOPTED FROM JOptionPane CODE
        for (int counter = 0, maxCounter = this.options.length; counter < maxCounter; counter++) {
            if (options[counter].equals(selectedValue)) {
                choice = counter;
                break;
            }
        }

        if (choice == null) {
            if (annoyCount > 6)
                this.ultimateWeapon = true;
            if (annoyCount > 8) {
                this.dialog.addKeyListener(new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == 27)
                            e.consume();
                    }

                    @Override
                    public void keyTyped(KeyEvent e) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // TODO Auto-generated method stub
                    }
                });
            }
            this.end(this.secretMessages[annoyCount], annoyCount + 1);
        } else if (choice == 1)
            System.exit(0);

        this.ultimateWeapon = false;
        this.setFocusable(true);
    }

    public void update() {
        if (!this.panel.update()) {
            this.end(this.endMessage, 0);
        }
    }

    // From KeyListener
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 27)
            System.exit(0); // Esc
        else if (key >= 37 && key <= 40)
            this.panel.changeDirection(key - 37); // Arrow Keys
        else if (key == 82)
            this.panel.changeSkin("rainbow"); // r
        else if (key == 65)
            this.panel.changeSkin("american"); // a
        else if (key == 67)
            this.panel.changeSkin("christmas"); // c
        else if (key == 68)
            this.panel.changeSkin("default"); // d
        else if (key == 32) 
            this.panel.reset(); // Space
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
