import java.awt.*;

public class Player extends Rectangle implements GameConstants, Runnable {

    private Image playerModel = new StreamHandler().loadPlayerModel();
    public Thread playerThread = new Thread(this);

    Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        if (playerModel != null) {
            playerModel = playerModel.getScaledInstance(playerWidth, playerHeight, Image.SCALE_DEFAULT);
        }
        playerThread.start();
    }

    public void move() {
        x += GameVariables.movementAmount;
        if (GameVariables.fire) {
            fire();
        }
    }

    private void fire() {
        if (globalMissiles.isEmpty()) {
            globalMissiles.add(new Missile(x + (playerWidth / 2) + (missileWidth), y - (playerHeight / 2)));
        }
    }

    public void draw(Graphics g) {
        if (GameVariables.fancyPlayer && playerModel != null) {
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
