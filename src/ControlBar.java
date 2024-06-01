import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ControlBar extends JToolBar implements Runnable, GameConstants {

    JButton left = new JButton("<");
    JButton fire = new JButton("Fire");
    JButton right = new JButton(">");
    Score scoreLabel = new Score();

    ControlBar() {
        left.setFocusable(false);
        right.setFocusable(false);
        fire.setFocusable(false);
        setSize(new Dimension(getWidth(), TOOLBAR_HEIGHT));
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(left);
        add(fire);
        add(right);
        add(scoreLabel, BorderLayout.WEST);
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
            GameVariables.fire = true;
        }
    }

    public void getKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left.getModel().setPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right.getModel().setPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameVariables.fire = false;
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
    }

    @Override
    public void run() {
        while (true) {
            handleMovement();
            try {
                Thread.sleep(1000L / 128);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
