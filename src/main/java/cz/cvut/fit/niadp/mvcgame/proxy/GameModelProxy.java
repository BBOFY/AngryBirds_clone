package cz.cvut.fit.niadp.mvcgame.proxy;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCmd;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.strategy.MissileMovingStrategyContext;

import java.util.List;

public class GameModelProxy implements IGameModel {

    private final IGameModel subject;

    public GameModelProxy(IGameModel model) {
        this.subject = model;
    }

    @Override
    public void moveCannonUp() {
        subject.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        subject.moveCannonDown();
    }

    @Override
    public void cannonShoot() {
        subject.cannonShoot();
    }

    @Override
    public void aimCannonUp() {
        subject.aimCannonUp();
    }

    @Override
    public void aimCannonDown() {
        subject.aimCannonDown();
    }

    @Override
    public void cannonPowerUp() {
        subject.cannonPowerUp();
    }

    @Override
    public void cannonPowerDown() {
        subject.cannonPowerDown();
    }

    @Override
    public void update() {
        subject.update();
    }

    @Override
    public List<AbsMissile> getMissiles() {
        return subject.getMissiles();
    }

    @Override
    public List<? extends GameObject> getGameObjects() {
        return subject.getGameObjects();
    }

    @Override
    public MissileMovingStrategyContext getMovingStrategyContext() {
        return subject.getMovingStrategyContext();
    }

    @Override
    public void toggleShootingMode() {
        subject.toggleShootingMode();
    }

    @Override
    public Object createMemento() {
        return subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
        subject.setMemento(memento);
    }

    @Override
    public void registerCommand(AbstractGameCmd cmd) {
        subject.registerCommand(cmd);
    }

    @Override
    public void undoLastCommand() {
        subject.undoLastCommand();
    }

    @Override
    public List<Enemy> getEnemies() {
        return subject.getEnemies();
    }
}
