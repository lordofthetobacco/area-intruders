import javax.swing.*;

public class Score extends JLabel implements GameConstants, Runnable {

    public Thread scoreThread = new Thread(this);
    
    private int currentScore;
    private JFrame gameFrame;

    Score(JFrame gameFrame) {
        this.gameFrame = gameFrame;
        currentScore = 100;
        setText("Score: " + currentScore);
        currentScore = 100;
        scoreThread.start();
    }

    public void addPoints() {
        currentScore += 10;
    }

    public int getScore() {
        return currentScore;
    }

    @Override
    public void run() {
        while (true) {
            gameFrame.setTitle("Score: " + currentScore);
            try {
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
                currentScore = 100;
            }
        }
    }
}