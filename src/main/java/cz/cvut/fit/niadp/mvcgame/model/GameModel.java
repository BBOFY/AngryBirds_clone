package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    public final MyEvent cannonMovedEvent;
    private IGameObjectFactory gameObjectFactory;

    public GameModel() {
        this.missiles = new ArrayList<>();
        this.gameObjectFactory = new GameObjectFactoryA(this);
        this.cannon = gameObjectFactory.createCannon(MvcGameConfig.INIT_CANNON_POSITION);
        this.cannonMovedEvent = new MyEvent();
    }

    public Vector2 getCannonPos() {
        return cannon.getPosition();
    }

    public void moveCannonUp() {
        cannon.moveUp();
        cannonMovedEvent.invoke();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        cannonMovedEvent.invoke();
    }

    public void cannonShoot() {
        missiles.add(cannon.shoot());
        cannonMovedEvent.invoke();
    }

    public void update() {
        // update missiles here
    }

}
