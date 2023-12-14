package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent_1;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.strategy.MissileMovingStrategyContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;

public class GameModel implements IGameModel {
    private final AbsCannon cannon;

    private final MissileMovingStrategyContext missileMovingStrategyContext;

    private final List<AbsMissile> missiles;

    private IGameObjectFactory gameObjectFactory;

    private final List<AbstractGameCommand> waitingCmds = new ArrayList<>();
    private final Stack<AbstractGameCommand> executedCmds = new Stack<>();

    public GameModel() {
        this.missiles = new ArrayList<>();
        this.gameObjectFactory = GameObjectFactoryA.getInstance();
        this.gameObjectFactory.init(this);
        this.cannon = gameObjectFactory.createCannon(MvcGameConfig.INIT_CANNON_POSITION);

        this.missileMovingStrategyContext = new MissileMovingStrategyContext();

        EventHolder.addMissileEvent.addListener(this::addMissile);
    }

    @Override
    public void moveCannonUp() {
        cannon.moveUp();
        EventHolder.cannonMovedEvent.invoke(cannon);
    }

    @Override
    public void moveCannonDown() {
        cannon.moveDown();
        EventHolder.cannonMovedEvent.invoke(cannon);
    }

    @Override
    public void cannonShoot() {
        var newRockets = cannon.shoot();
        if (newRockets.isEmpty()) {
            return;
        }
        missiles.addAll(newRockets);
        EventHolder.missileLaunchedEvent.invoke(missiles.get(0));
    }

    @Override
    public void aimCannonUp() {
        this.cannon.aimUp();
    }

    @Override
    public void aimCannonDown() {
        this.cannon.aimDown();
    }

    @Override
    public void cannonPowerUp() {
        this.cannon.powerUp();
    }

    @Override
    public void cannonPowerDown() {
        this.cannon.powerDown();
    }

    @Override
    public void update() {
        runCommands();

        moveMissiles();
        destroyMissiles();
        EventHolder.gameObjectMovedEvent.invoke();
    }

    private void runCommands() {
        for (var c : waitingCmds) {
            c.doExecute();
            executedCmds.push(c);
        }
        waitingCmds.clear();
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

    @Override
    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    private void addMissile(AbsMissile missile) {
        missiles.add(missile);
    }

    @Override
    public List<? extends GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
    }

    @Override
    public MissileMovingStrategyContext getMovingStrategyContext() {
        return missileMovingStrategyContext;
    }

    @Override
    public void toggleShootingMode() {
        cannon.toggleShootingMode();
    }

    private static class Memento {
        private Vector2 cannonPosition;

    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPosition = cannon.position.clone();
        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.position = gameModelSnapshot.cannonPosition;
        EventHolder.gameObjectMovedEvent.invoke();
    }



    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        waitingCmds.add(cmd);
    }

    @Override
    public void undoLastCommand() {
        if (executedCmds.empty()) return;
        executedCmds.pop().unExecute();
    }


}
