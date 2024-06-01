import java.awt.*;
import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

public class Scoreboard extends JFrame implements GameConstants{
    private Score score;
    Scoreboard(Score score) {
        this.score = score;
        setSize(new Dimension(200,200));
        setTitle("Scoreboard");
        setResizable(false);
        setContentPane(new ScorePanel());
    }

    public void newScore() {
        setContentPane(new InputScore(this, score));
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }

    private class ScorePanel extends JPanel {
        
        private JList list = new JList<>(getStringsFromMap());
        
        ScorePanel() {
            add(list);
        }


        private String[] getStringsFromMap() {
            ArrayList<String> outputArrayList = new ArrayList<>();
            for (Map.Entry<String, Integer> element : globalScores.entrySet()) {
                outputArrayList.add(element.getKey()+ " : " + element.getValue());
            }
            return outputArrayList.toArray(new String[outputArrayList.size()]);
        }
    }
    private class InputScore extends JPanel {
        private JTextField alias= new JTextField();
        private JButton add = new JButton("Add");
        private Score score;
        InputScore(Scoreboard scoreboard, Score score) {
            this.score = score;
            setLayout(new GridLayout(2,1));
            add(new JLabel("Name")); add(alias);
            alias.addActionListener(e -> {
                globalScores.put(alias.getText(), score.getScore());
                scoreboard.toggleVisibility();
            });
        }
    }
}
