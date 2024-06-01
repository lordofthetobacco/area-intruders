import java.awt.*;

public class InvaderMissile extends Rectangle implements GameConstants {
    InvaderMissile(int x, int y) {
        super(x, y, missileWidth, missileHeight);
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, missileWidth, missileHeight);
    }

    public void move() {
        y += missileSpeed;
    }
}
