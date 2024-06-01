import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamHandler implements GameConstants {

    private final String savePath = "saves";
    private final String assetPath = "assets";

    private static final Pattern imagePattern = Pattern.compile("\\.(jpg|png|jpeg)$", Pattern.CASE_INSENSITIVE);

    StreamHandler() {
        try {
            loadBackgroundsFromDirectory();
        } catch (IOException e) {
            new JOptionPane("No backgrounds found in path '(Game path)/assets/backgrounds'.");
        }
        loadSettings();
        loadScore();
    }

    private boolean checkIfImage(String value) {
        Matcher match = imagePattern.matcher(value);
        return match.matches();
    }

    private void loadBackgroundsFromDirectory() throws IOException {
        DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(assetPath, "backgrounds"));
        for (Path imagePath : dirStream) {
            if (checkIfImage(imagePath.toString())) {
                globalBackgrounds.add(ImageIO.read(imagePath.toFile()));
            }
        }
        dirStream.close();
    }

    public void saveSettings() {
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(GameVariables.CURRENT_BACKGROUND_MODEL_INDEX).append("\n");
        dataBuilder.append(GameVariables.FANCY_PLAYER).append("\n");
        dataBuilder.append(GameVariables.DIFFICULTY);
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(savePath, "settings"), Charset.defaultCharset());
            writer.write(dataBuilder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            new JOptionPane("Could not save the settings.");
        }
    }

    public Image loadPlayerModel() {
        Image image = null;
        try {
            image = ImageIO.read(Paths.get(assetPath, "player", "player_model.png").toFile());
            return image;
        } catch (IOException e) {
            return image;
        }
    }

    public void saveScore() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(savePath, "scores"), Charset.defaultCharset());
            for (Map.Entry<String, Integer> entry : globalScores.entrySet()) {
                writer.write(entry.getKey() + "|" + entry.getValue());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            new JOptionPane("Could not save scoreboard to file.");
        }
    }

    public void loadSettings() {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(savePath, "settings"));
            String[] readSettings = new String[3];
            String line;
            int c = 0;
            while ((line = reader.readLine()) != null) {
                readSettings[c] = line;
                c++;
            }
            GameVariables.CURRENT_BACKGROUND_MODEL_INDEX = Integer.parseInt(readSettings[0]);
            GameVariables.FANCY_PLAYER = Boolean.parseBoolean(readSettings[1]);
            GameVariables.DIFFICULTY = Difficulty.valueOf(readSettings[2]);
            reader.close();
        } catch (IOException e) {
            new JOptionPane("No settings save file found.");
        }
    }

    public void loadScore() {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(savePath, "scores"), Charset.defaultCharset());
            String[] tmpSplitter;
            String line;
            while ((line = reader.readLine()) != null) {
                tmpSplitter = line.split("\\|");
                globalScores.put(tmpSplitter[0], Integer.valueOf(tmpSplitter[1]));
            }
            reader.close();
        } catch (IOException e) {
            new JOptionPane("Could not load scoreboard from file.");
        }
    }
}