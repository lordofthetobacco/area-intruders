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
        saveButton.addActionListener(e -> {
            GameVariables.DIFFICULTY = settingsPanel.getNewDifficulty();
            GameVariables.CURRENT_BACKGROUND_MODEL_INDEX = settingsPanel.getNewBackgroundModelIndex();
            GameVariables.FANCY_PLAYER = settingsPanel.getNewPlayerModelSetting();
            toggleVisibility();
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }

    private class SettingsPanel extends JPanel {

        private JComboBox<Difficulty> difficultySelector = new JComboBox<>(Difficulty.values());
        private JComboBox<String> backgroundSelector = new JComboBox<>();
        private JCheckBox playerSwitch = new JCheckBox();

        SettingsPanel() {
            setLayout(new GridLayout(3, 2, 0, 5));
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            init();
        }

        private void init() {
            add(new JLabel("Difficulty"));
            add(difficultySelector);
            playerSwitch.setSelected(!GameVariables.FANCY_PLAYER);
            add(new JLabel("Simple player model"));
            add(playerSwitch);
            add(new JLabel("Background"));
            add(backgroundSelector);
        }

        public Difficulty getNewDifficulty() {
            return (Difficulty) difficultySelector.getSelectedItem();
        }

        public int getNewBackgroundModelIndex() {
            return backgroundSelector.getSelectedIndex();
        }

        public boolean getNewPlayerModelSetting() {
            return playerSwitch.getModel().isSelected();
        }
    }
}
