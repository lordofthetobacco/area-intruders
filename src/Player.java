import java.awt.*;


public class Player extends Rectangle implements GameConstants, Runnable{

    Player(int x, int y, int width, int height) {
        super(x,y,width,height);
        new Thread(this).start();
    }

    public void move() {
        x += GameVariables.MOVEMENT_AMOUNT;
    }


    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x,y,height, width);
    }


    @Override
    public void run() {
        while (true) {
                move();
            try{
                Thread.sleep(1000L / 120);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

