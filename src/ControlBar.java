import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ControlBar extends JToolBar implements Runnable, GameConstants{

    JButton left = new JButton("<");
    JButton fire = new JButton("Fire");
    JButton right = new JButton(">");

    private Player player;

    ControlBar(Player player) {
        this.player = player;
        left.setFocusable(false);
        right.setFocusable(false);
        fire.setFocusable(false);
        setSize(new Dimension(getWidth(), TOOLBAR_HEIGHT));
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(left);
        add(fire);
        add(right);
        new Thread(this).start();
    }

    public void getKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left.getModel().setPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right.getModel().setPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            right.getModel().setPressed(true);
        }
    }

    public void getKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left.getModel().setPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right.getModel().setPressed(false);
        }
    }

    public void handleMovement() {
        if (left.getModel().isPressed()) {
            GameVariables.MOVEMENT_AMOUNT = -GameVariables.PLAYER_SPEED;
        }

        if (right.getModel().isPressed()) {
            GameVariables.MOVEMENT_AMOUNT = GameVariables.PLAYER_SPEED;
        }

        if (!left.getModel().isPressed() && !right.getModel().isPressed()) {
            GameVariables.MOVEMENT_AMOUNT = 0;
        }

        if (fire.getModel().isPressed()) {
            if (missiles.isEmpty()) {
                missiles.add(new Missile(player.x + PLAYER_WIDTH / 2 - GameConstants.MISSILE_WIDTH, player.y + PLAYER_HEIGHT));
            }
        }
    }


    @Override
    public void run() {
        while (true) {
            handleMovement();
            try {
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
            }
        }
    }

}
