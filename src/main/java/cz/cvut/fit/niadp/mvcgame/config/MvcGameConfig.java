package cz.cvut.fit.niadp.mvcgame.config;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public class MvcGameConfig {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 700;
    public static final String GAME_TITLE = "The NI-ADP MvcGame";

    public static final int ENEMY_SPRITE_WIDTH = 30;
    public static final int ENEMY_SPRITE_HEIGHT = 26;

    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String LEFT_KEY = "LEFT";
    public static final String RIGHT_KEY = "RIGHT";
    public static final String W_KEY = "W";
    public static final String S_KEY = "S";
    public static final String SHOOT_KEY = "SPACE";
    public static final String SECONDARY_ACTION_KEY = "ENTER";
    public static final String TOGGLE_MOVING_STRATEGY_KEY = "M";
    public static final String TOGGLE_SHOOTING_MODE_KEY = "N";
    public static final String EXIT_KEY = "ESCAPE";

    public static final Vector2 INIT_CANNON_POSITION = new Vector2(10, SCREEN_HEIGHT / 2.0);
    public static final double MOVE_STEP = 10;
    public static final Vector2 CANNON_UPPER_BOUND = new Vector2(10, SCREEN_HEIGHT * 0.125);
    public static final Vector2 CANNON_LOWER_BOUND = new Vector2(10, SCREEN_HEIGHT * 0.875);
    public static final double ANGLE_STEP = Math.toRadians(5);
    public static final double MAX_CANNON_INCLINATION = Math.toRadians(-80);
    public static final double MAX_CANNON_DEPRESSION = Math.toRadians(80);
    public static final int POWER_STEP = 1;
    public static final int INIT_POWER = 10;

    public static final int MIN_CANNON_POWER = 1;
    public static final int MAX_CANNON_POWER = 50;
    public static final double INIT_ANGLE = 0;
    public static final double GRAVITY = 9.81;

    public static final String CANNON_IMAGE_RESOURCE = "/images/cannon.png";
    public static final String MISSILE_IMAGE_RESOURCE = "/images/missile.png";
    public static final String BACKGROUND_IMG_RESOURCE = "/images/back.jpg";

    public static final String CANNON_FIRE_AUDIO_RESOURCE = "/audio/cannonShoot.mp3";
    public static final String CANNON_MOVE_AUDIO_RESOURCE = "/audio/cannonMove.mp3";

    public static final long MISSILE_LIFETIME_MILLS = 10000;
    public static final int MIN_AMOUNT_SHOTS = 1;
    public static final int MAX_AMOUNT_SHOTS = 5;
    public static final String INC_SHOTS_KEY = "E";
    public static final String DEC_SHOTS_KEY = "Q";
    public static final String UNDO_LAST_CMD_KEY = "BACK_SPACE";

    public static final String LIGHT_ENEMY_A_SPRITE_PATH = "/images/enemy1_org.png";
    public static final String MEDIUM_ENEMY_A_SPRITE_PATH = "/images/enemy2.png";
    public static final String HEAVY_ENEMY_A_SPRITE_PATH = "/images/enemy3.png";
    public static final String DEAD_ENEMY_A_SPRITE_PATH = "/images/enemyDead.png";
    public static final String OBSTACLE_PATH = "/images/bound.png";
    public static final int NUMBER_OF_ENEMIES = 10;



    public static final byte CANNON_LAYER_BIT = 1;
    public static final byte MISSILE_LAYER_BIT = 1 << 1;
    public static final byte ENEMY_LAYER_BIT = 1 << 2;
    public static final byte OBSTACLE_LAYER_BIT = 1 << 3;
    public static final Vector2 CANNON_SPRITE_SIZE = new Vector2(25, 69);
    public static final Vector2 OBSTACLE_SPRITE_SIZE = new Vector2(30, 30);


    public static final String TOGGLE_SIMPLE_SHOOT_CHEAT = "toggle simple";

    private MvcGameConfig(){

    }

}