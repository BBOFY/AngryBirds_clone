package cz.cvut.fit.niadp.mvcgame.config;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public class MvcGameConfig {

    public static final int MOVE_STEP = 10;
    public static final int MAX_X = 720;
    public static final int MAX_Y = 480;
    public static final String GAME_TITLE = "The NI-ADP MvcGame";

    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String SHOOT_KEY = "SPACE";
    public static final String EXIT_KEY = "ESCAPE";

    public static final Vector2 INIT_CANNON_POSITION = new Vector2(10, MAX_Y/2);
    public static final String CANNON_IMAGE_RESOURCE = "images/cannon.png";
    public static final String MISSILE_IMAGE_RESOURCE = "image/missile.png";

    private MvcGameConfig(){

    }

}