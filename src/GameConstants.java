import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameConstants {
    // Game Constants
    int frameHeight = 720;
    int frameWidth = (int) (frameHeight * 1.777f);
    int spawnCountInFirstRow = 10;
    int toolbarHeight = 35;
    int menubarHeight = 35;

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
    ArrayList<InvaderMissile> globalInvaderMissiles = new ArrayList<>();
    ArrayList<String> globalBackgrounds = new ArrayList<>();
    ArrayList<Invader> globalInvaders = new ArrayList<>();
    
    // Thread Array
    ArrayList<Thread> globalThreads = new ArrayList<>();

    // Score Elements
    HashMap<String, Integer> globalScores = new HashMap<>();
}
