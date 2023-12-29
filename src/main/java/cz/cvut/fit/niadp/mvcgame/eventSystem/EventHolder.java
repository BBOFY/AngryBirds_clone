package cz.cvut.fit.niadp.mvcgame.eventSystem;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsObstacle;

public class EventHolder {

    // From model
    public static final MyEvent gameObjectMovedEvent = new MyEvent();
    public static final MyEvent_1<AbsCannon> cannonMovedEvent = new MyEvent_1<>();
    public static final MyEvent_1<AbsMissile> missileLaunchedEvent = new MyEvent_1<>();


    // From controller
    public static final MyEvent secondaryActionEvent = new MyEvent();
    public static final MyEvent incShotsEvent = new MyEvent();
    public static final MyEvent decShotsEvent = new MyEvent();

    // For communication
    public static final MyEvent_1<AbsMissile> addMissileEvent = new MyEvent_1<>();


    public static final MyEvent_1<AbsObstacle> addObstacleEvent = new MyEvent_1<>();


    public static final MyEvent toggleDebugEvent = new MyEvent();
    public static final MyEvent missileHitEvent = new MyEvent();
    public static MyEvent toggleMuteEvent = new MyEvent();
}
