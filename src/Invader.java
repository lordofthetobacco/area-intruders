import java.awt.*;

public class Invader extends Rectangle implements GameConstants {

    private int speed = invaderSpeed;

    Invader(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void invertX() {
        speed *= -1;
    }

    public void move() {
        x += speed;
    }

    public void shoot() {
        globalEnemyMissiles.add(new InvaderMissile(x + (playerWidth / 2), y + missileWidth));
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
    }
}