import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class Score extends JLabel implements GameConstants, Runnable {

    public Thread scoreThread = new Thread(this);
    
    private static int currentScore;
    
    Score() {
        currentScore = 100;
        setText("Score: " + currentScore);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(new Dimension(frameWidth, toolbarHeight));
        currentScore = 100;
    }

    public static void addPoints() {
        currentScore += 10;
    }

    public int getScore() {
        return currentScore;
    }

    @Override
    public void run() {
        while (true) {
            setText("Score: " + currentScore);
            try {
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
                currentScore = 100;
            }
        }
    }
}