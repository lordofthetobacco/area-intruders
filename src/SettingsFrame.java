import javax.swing.*;
import java.awt.*;


public class SettingsFrame extends JFrame implements GameConstants {

    private final SettingsPanel settingsPanel = new SettingsPanel();
    JButton saveButton = new JButton("Save");

    SettingsFrame() {
        setTitle("Settings");
        setSize(new Dimension(450, 500));
        setResizable(false);
        add(settingsPanel);
        saveButton.addActionListener(_ -> {
            GameVariables.DIFFICULTY = settingsPanel.getNewDifficulty();
            GameVariables.CURRENT_BACKGROUND_MODEL_INDEX = settingsPanel.getNewBackgroundModelIndex();
            GameVariables.CURRENT_PLAYER_MODEL_INDEX = settingsPanel.getNewPlayerModelIndex();
            GameVariables.CURRENT_INVADER_MODEL_INDEX = settingsPanel.getNewInvaderModelIndex();
            toggleVisibility();
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }

    private class SettingsPanel extends JPanel{

        JComboBox<Difficulty> difficultySelector = new JComboBox<>(Difficulty.values());
        JComboBox<String> backgroundSelector = new JComboBox<>();
        JComboBox<String> playerSelector = new JComboBox<>();
        JComboBox<String> invaderSelector = new JComboBox<>();

        SettingsPanel() {
            setLayout(new GridLayout(4,2, 0,5));
            setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            init();
        }

        private void init() {
            add(new JLabel("Difficulty")); add(difficultySelector);
            add(new JLabel("Invader")); add(backgroundSelector);
            add(new JLabel("Player")); add(playerSelector);
            add(new JLabel("Background")); add(invaderSelector);
        }

        public Difficulty getNewDifficulty() {
            return (Difficulty) difficultySelector.getSelectedItem();
        }

        public int getNewInvaderModelIndex() {
            return invaderSelector.getSelectedIndex();
        }

        public int getNewPlayerModelIndex() {
            return playerSelector.getSelectedIndex();
        }

        public int getNewBackgroundModelIndex() {
            return backgroundSelector.getSelectedIndex();
        }
    }
}
