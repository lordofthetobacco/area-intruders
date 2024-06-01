public class GameVariables {
    // Preferences
    static int CURRENT_BACKGROUND_MODEL_INDEX = 0;
    static boolean FANCY_PLAYER = true;
    static Difficulty DIFFICULTY = Difficulty.MEDIUM;
    static int gameSpeed = getGameSpeed(DIFFICULTY);
    // Player Movement
    static int MOVEMENT_AMOUNT = 0;
    static int PLAYER_SPEED = 2;
    static boolean fire = false;
    // Invader Movement
    static final int INVADER_SPEED = 1;

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
