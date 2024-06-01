import java.awt.*;

public class Missile extends Rectangle implements GameConstants {
    Missile(int x, int y) {
        super(x, y, missileWidth, missileHeight);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, missileWidth, missileHeight);
    }

    public void move() {
        y -= missileSpeed;
    }
}
