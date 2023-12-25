package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCmd;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.strategy.movingStrategy.MissileMovingStrategyContext;

import java.util.List;

public interface IGameModel {

    void moveCannonUp();

    void moveCannonDown();

    void cannonShoot();

    void aimCannonUp();

    void aimCannonDown();

    void cannonPowerUp();

    void cannonPowerDown();

    AbsCannon getCannon();

    void update();

    List<AbsMissile> getMissiles();

    List<? extends GameObject> getGameObjects();

    MissileMovingStrategyContext getMovingStrategyContext();

    void toggleShootingMode();

    Object createMemento();

    void setMemento(Object memento);

    void registerCommand(AbstractGameCmd cmd);
    void undoLastCommand();

    List<Enemy> getEnemies();
}
