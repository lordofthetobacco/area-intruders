import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class Scoreboard extends JFrame implements GameConstants{
    




    private class ScorePanel extends JPanel {
        
        private JList list = new JList<>(getStringsFromMap());
        
        ScorePanel() {
            add(list);
        }

        public void toggleVisibility() {
            setVisible(!isVisible());
        }

        private String[] getStringsFromMap() {
            ArrayList<String> output = new ArrayList<>();
            for (Map.Entry<String, Integer> element : globalScores.entrySet()) {
                output.add(element.getKey()+ " : " + element.getValue());
            }
            return (String[]) output.toArray();
        }
    }
}
