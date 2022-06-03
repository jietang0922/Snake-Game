package util;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {

    private Panel panel;
    private final String endMessage = "Game Over";
    private JOptionPane pane;
    private JDialog dialog;
    private boolean ultimateWeapon;
    private String[] options = { "Restart", "Quit" };
    private String[] secretMessages = {
            "Choose an option",
            "Please, just choose an option",
            "Please don't be annoying",
            "Stop being annoying",
            "Don't make me do this",
            "You're pushing it",
            "Alright you're done",
            "Now you can't even close it",
            "Funny",
            "Now you can't do that either"
    };
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
        this.pane = new JOptionPane(this.endMessage, JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, this.options, null);
        this.dialog = pane.createDialog("GAME OVER");
        this.dialog.setFocusable(true);

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
        if(this.ultimateWeapon) {
            this.dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }

        this.pane.setMessage(message);
        this.dialog.setVisible(true);

        Object selectedValue = pane.getValue();
        Integer choice = null;

        // ADAPTED FROM JOptionPane CODE
        for (int counter = 0, maxCounter = this.options.length; counter < maxCounter; counter++) {
            if (options[counter].equals(selectedValue)) {
                choice = counter;
                break;
            }
        }

        System.out.println(choice);

        if (choice == null) {
            System.out.println("CLOSED " + annoyCount);
            if(annoyCount > 6) this.ultimateWeapon = true;
            if(annoyCount > 8) {
                this.dialog.addKeyListener(new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 27) e.consume();
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
            this.dispose();

        this.ultimateWeapon = false;
        this.setFocusable(true);
    }

    public void update() {
        if (!this.panel.update())
            this.end(this.endMessage, 0);
    }

    // From KeyListener
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 27) System.exit(0); 
        else if (key >= 37 && key <= 40) this.panel.changeDirection(key - 37);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
