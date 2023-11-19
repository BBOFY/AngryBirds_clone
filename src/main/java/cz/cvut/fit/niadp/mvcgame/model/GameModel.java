package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent_1;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameModel {

    public final MyEvent gameObjectMovedEvent;
    public final MyEvent_1<AbsCannon> cannonMovedEvent;
    public final MyEvent_1<AbsMissile> missileLaunchedEvent;

    private static GameModel inst;
    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    private IGameObjectFactory gameObjectFactory;

    public static GameModel getInst() {
        if (inst == null) {
            inst = new GameModel();
        }
        return inst;
    }

    private GameModel() {
        this.missiles = new ArrayList<>();
        this.gameObjectFactory = GameObjectFactoryA.getInstance();
        this.cannon = gameObjectFactory.createCannon(MvcGameConfig.INIT_CANNON_POSITION);
        this.gameObjectMovedEvent = new MyEvent();
        this.cannonMovedEvent = new MyEvent_1<>();
        this.missileLaunchedEvent = new MyEvent_1<>();
    }

    public void moveCannonUp() {
        cannon.moveUp();
        gameObjectMovedEvent.invoke();
        cannonMovedEvent.invoke(cannon);
    }

    public void moveCannonDown() {
        cannon.moveDown();
        gameObjectMovedEvent.invoke();
        cannonMovedEvent.invoke(cannon);
    }

    public void cannonShoot() {
        AbsMissile newMissile = cannon.shoot();
        missiles.add(newMissile);
        gameObjectMovedEvent.invoke();
        missileLaunchedEvent.invoke(newMissile);
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
        gameObjectMovedEvent.invoke();
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
        gameObjectMovedEvent.invoke();
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
        gameObjectMovedEvent.invoke();
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
        gameObjectMovedEvent.invoke();
    }

    public void update() {
        moveMissiles();
        destroyMissiles();
        gameObjectMovedEvent.invoke();
    }

    private void destroyMissiles() {
        missiles.removeAll(
            missiles.stream().filter(missile ->
                missile.getPos().x > MvcGameConfig.SCREEN_WIDTH || missile.getPos().y > MvcGameConfig.SCREEN_HEIGHT
            ).toList()
        );

    }

    private void moveMissiles() {
        missiles.forEach(missile -> missile.move(new Vector2(MvcGameConfig.MOVE_STEP, 0)));
    }

    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    public List<? extends GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
    }
}
