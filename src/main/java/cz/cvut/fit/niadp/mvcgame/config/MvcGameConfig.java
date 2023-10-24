package cz.cvut.fit.niadp.mvcgame.config;

import cz.cvut.fit.niadp.mvcgame.model.Position;

public class MvcGameConfig {

    public static final int MOVE_STEP = 10;
    public static final int MAX_X = 720;
    public static final int MAX_Y = 480;
    public static final String GAME_TITLE = "The NI-ADP MvcGame";

    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String EXIT_KEY = "ESCAPE";

    public static final Position INIT_CANNON_POSITION = new Position(10, MAX_Y/2);
    public static final String CANNON_IMAGE_RESOURCE = "images/cannon.png";

    private MvcGameConfig(){

    }

}