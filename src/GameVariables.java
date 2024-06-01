import java.util.Date;
import java.util.Random;

public class GameVariables  {
    // Preferences
    static int currentBackgroundModelIndex = 0;
    static boolean fancyPlayer = true;
    static Difficulty gameDifficulty = Difficulty.MEDIUM;
    static int gameSpeed = getGameSpeed(gameDifficulty);
    // Player Movement
    static int movementAmount = 0;
    static int playerSpeed = 2;
    static boolean fire = false;
    // Invader Movement
    static final int INVADER_SPEED = 1;
    static Thread enemiesShootingThread = new Thread();
    // Speed Setter
    private static int getGameSpeed(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return 64;
            case MEDIUM:
                return 128;
            case HARD:
                return 256;
            case INSANE:
                return 128;
            default:
                return 128;
        }
    }


}
