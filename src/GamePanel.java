import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
public class GamePanel extends JPanel implements GameConstants, Runnable{

    Player player;
    Image background;
    Graphics bgGraphics;

    GamePanel() {
        setFocusable(true);
        setBackground(new Color(0,0,0));
        spawnPlayer();
        new Thread(this).start();
    }

    private void spawnPlayer() {
        player = new Player((FRAME_WIDTH / 2 ) - PLAYER_WIDTH,FRAME_HEIGHT - PLAYER_HEIGHT - TOOLBAR_HEIGHT - MENUBAR_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public Player getPlayer() {
        return player;
    }

    public void paint(Graphics g) {
        try {
            background = ImageIO.read(Paths.get("assets", "bg1.jpg").toFile());
        } catch (IOException e) {
            background = createImage(getWidth(), getHeight());
        }
        bgGraphics = background.getGraphics();
        draw(bgGraphics);
        g.drawImage(background,0,0,this);
    }

    public void checkForCollisions() {
        missiles.removeIf(missile -> {
            if (missile.y == 0) {
                missile.destroy();
                return true;
            }
            return false;
        });

        if (player.x <= 0) {
            player.x = 0;
        }
        if (player.x >= FRAME_WIDTH - PLAYER_WIDTH) {
            player.x = FRAME_WIDTH - PLAYER_WIDTH;
        }
    }

    public void update() {
        checkForCollisions();
        repaint();
    }

    public void draw(Graphics g) {
        player.draw(g);

        for (Missile missile : missiles) {
            missile.draw(g);
        }
    }

    @Override
    public void run() {
        while(true) {
                update();
            try{
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
