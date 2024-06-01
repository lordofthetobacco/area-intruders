import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame implements GameConstants {

    private final StreamHandler streamHandler = new StreamHandler();
    private final SettingsFrame settingsWindow = new SettingsFrame();
    private final GamePanel gamePanel = new GamePanel(this);
    private final ControlBar controlBar = new ControlBar();

    GameFrame() {
        add(gamePanel);
        add(createMenuBar(), BorderLayout.NORTH);
        setTitle("Area Intruders");
        setResizable(false);
        setFocusable(true);
        setSize(new Dimension(frameWidth, frameHeight));
        add(controlBar, BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new KeyController());
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                streamHandler.saveScore();
                streamHandler.saveSettings();
                e.getWindow().dispose();
            }
        });
    }

    private JMenuBar createMenuBar() {
        JMenuBar output = new JMenuBar();

        JMenuItem settingsButton = new JMenuItem("Settings");
        JMenuItem rankButton = new JMenuItem("Ranking");

        settingsButton.addActionListener(e -> {
            settingsWindow.toggleVisibility();
        });

        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(settingsButton);
        gameMenu.add(rankButton);

        output.add(gameMenu);
        output.setSize(new Dimension(getWidth(), MENUBAR_HEIGHT));
        return output;
    }

    private class KeyController extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            controlBar.getKeyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            controlBar.getKeyReleased(e);
        }
    }
}
