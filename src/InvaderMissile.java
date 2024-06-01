import java.awt.*;

public class InvaderMissile extends Rectangle implements GameConstants, Runnable {

    InvaderMissile(int x, int y) {
        super(x, y, missileWidth, missileHeight);
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, missileWidth, missileHeight);
    }

    public void travel() {
        y += missileSpeed;
    }

    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        while (true) {
            travel();
            try {
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
