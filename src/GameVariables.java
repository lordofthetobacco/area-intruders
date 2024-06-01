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
    static int shootingDelayInSeconds = getShootingDelay(gameDifficulty); 
    // Speed Setter
    private static int getGameSpeed(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return 64;
            case MEDIUM:
                return 128;
            case HARD:
                return 256;
            default:
                return 128;
        }
    }

    private static int getShootingDelay(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return 4;
            case MEDIUM:
                return 2;
            case HARD:
                return 1;
            default:
                return 2;
        }
    }
}
