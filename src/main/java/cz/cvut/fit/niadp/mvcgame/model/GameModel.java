package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameModel {
    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    public final MyEvent gameObjectMovedEvent;
    private IGameObjectFactory gameObjectFactory;

    public GameModel() {
        this.missiles = new ArrayList<>();
        this.gameObjectFactory = new GameObjectFactoryA(this);
        this.cannon = gameObjectFactory.createCannon(MvcGameConfig.INIT_CANNON_POSITION);
        this.gameObjectMovedEvent = new MyEvent();
    }

    public Vector2 getCannonPos() {
        return cannon.getPos();
    }

    public void moveCannonUp() {
        cannon.moveUp();
        gameObjectMovedEvent.invoke();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        gameObjectMovedEvent.invoke();
    }

    public void cannonShoot() {
        missiles.add(cannon.shoot());
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

    public List<GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
    }
}
