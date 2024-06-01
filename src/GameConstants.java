import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameConstants {
    // Game Constants
    int frameHeight = 720;
    int frameWidth = (int) (frameHeight * 1.777f);
    int SPAWN_COUNT_IN_FIRST_ROW = 10;
    int TOOLBAR_HEIGHT = 35;
    int MENUBAR_HEIGHT = 35;

    // Player Settings
    int playerWidth = 40;
    int playerHeight = (int) (playerWidth * 1.777f);

    // Missile Settings
    int missileHeight = 20;
    int missileWidth = 8;
    int missileSpeed = 3;

    // Invader Settings
    int invaderSize = 24;
    int invaderSpeed = 1;

    // Object Arrays
    ArrayList<Missile> globalMissiles = new ArrayList<>();
    ArrayList<InvaderMissile> globalEnemyMissiles = new ArrayList<>();
    ArrayList<Image> globalBackgrounds = new ArrayList<>();
    ArrayList<Invader> globalInvaders = new ArrayList<>();

    // Score Elements
    HashMap<String, Integer> globalScores = new HashMap<>();
}
