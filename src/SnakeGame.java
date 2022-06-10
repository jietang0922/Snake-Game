import netscape.javascript.JSException;
import util.JSONReader;

public class SnakeGame {
    public static void main(String[] args) throws Exception {
        JSONReader.read();
        Frame frame = new Frame("Snake Game");
        frame.start();
    }
}
