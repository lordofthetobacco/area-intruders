import java.util.ArrayList;

public interface GameConstants {
//    Game Constants
    int FRAME_HEIGHT = 720;
    int FRAME_WIDTH = (int)(FRAME_HEIGHT * 1.777f);
    int GAME_OBJECT_SIZE = 40;
    int SPAWN_COUNT_IN_FIRST_COLUMN = 10;
    int TOOLBAR_HEIGHT = 35;
    int MENUBAR_HEIGHT = 35;

//    Player Settings
    int PLAYER_WIDTH = 40;
    int PLAYER_HEIGHT = (int)(PLAYER_WIDTH * 1.777f);


//    Missile Settings
    int MISSILE_HEIGHT = 20;
    int MISSILE_WIDTH = 8;
    int MISSILE_SPEED = 3;

//    Invader Settings
    int INVADER_SIZE = 24;
    int INVADER_SPEED = 1;


//    Object Arrays
    ArrayList<Missile> missiles = new ArrayList<>();
    ArrayList<Invader> invaders = new ArrayList<>();
}

