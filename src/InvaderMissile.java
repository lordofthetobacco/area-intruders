import java.awt.*;

public class SinisterMissile extends Rectangle implements GameConstants, Runnable {
    private boolean destroy = false;

    SinisterMissile(int x, int y) {
        super(x,y, MISSILE_WIDTH, MISSILE_HEIGHT);
        new Thread(this).start();
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

    public void travel() {
        y -= MISSILE_SPEED;
    }

    void destroy() {
        destroy = true;
    }

    @Override
    public void run() {
        while (true) {
            if (destroy) {
                Thread.currentThread().interrupt();
            }
            travel();
            try {
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
