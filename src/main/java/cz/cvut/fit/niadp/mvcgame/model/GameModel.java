package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent_1;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.strategy.MissileMovingStrategyContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameModel {

    public static double delta = 0;

    public final MyEvent gameObjectMovedEvent;
    public final MyEvent_1<AbsCannon> cannonMovedEvent;
    public final MyEvent_1<AbsMissile> missileLaunchedEvent;

    private static GameModel inst;
    private final AbsCannon cannon;

    private MissileMovingStrategyContext missileMovingStrategyContext;

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

        this.missileMovingStrategyContext = new MissileMovingStrategyContext();


    }

    public void moveCannonUp() {
        cannon.moveUp();
        cannonMovedEvent.invoke(cannon);
    }

    public void moveCannonDown() {
        cannon.moveDown();
        cannonMovedEvent.invoke(cannon);
    }

    public void cannonShoot() {
        var newRockets = cannon.shoot();
        if (newRockets.isEmpty()) {
            return;
        }
        missiles.addAll(newRockets);
        missileLaunchedEvent.invoke(missiles.get(0));
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
    }

    public void update() {
        moveMissiles();
        destroyMissiles();
        gameObjectMovedEvent.invoke();
    }

    private void destroyMissiles() {
        missiles.removeAll(
            missiles.stream().filter(missile ->
                missile.position.x > MvcGameConfig.SCREEN_WIDTH || missile.position.y > MvcGameConfig.SCREEN_HEIGHT
                || missile.position.x < 0 || missile.position.y < 0
                    || missile.getAge() > MvcGameConfig.MISSILE_LIFETIME_MILLS
            ).toList()
        );

    }

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
    }

    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    public List<? extends GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
    }

    public MissileMovingStrategyContext getMovingStrategyContext() {
        return missileMovingStrategyContext;
    }

    public void toggleShootingMode() {
        cannon.toggleShootingMode();
    }

    private static class Memento {
        private Vector2 cannonPosition;

    }

    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPosition = cannon.position.clone();
        return gameModelSnapshot;
    }

    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.position = gameModelSnapshot.cannonPosition;
        gameObjectMovedEvent.invoke();
    }


}
