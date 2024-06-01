import java.awt.*;

public class Player extends Rectangle implements GameConstants, Runnable {

    private Image playerModel = new StreamHandler().loadPlayerModel();
    private Thread currentThread = new Thread(this);

    Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        if (playerModel != null) {
            playerModel = playerModel.getScaledInstance(playerWidth, playerHeight, Image.SCALE_DEFAULT);
        }
        currentThread.start();
    }

    public void move() {
        x += GameVariables.MOVEMENT_AMOUNT;
    }

    public void fire() {
        if (globalMissiles.isEmpty() && GameVariables.fire) {
            globalMissiles.add(new Missile(x + (playerWidth / 2) + (missileWidth), y - (playerHeight / 2)));
        }
    }

    public void draw(Graphics g) {
        if (GameVariables.FANCY_PLAYER && playerModel != null) {
            g.drawImage(playerModel, x, y, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, height, width);
        }
    }

    @Override
    public void run() {
        int gameSpeed = GameVariables.gameSpeed;
        while (true) {
            move();
            try {
                Thread.sleep(1000L / gameSpeed);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
