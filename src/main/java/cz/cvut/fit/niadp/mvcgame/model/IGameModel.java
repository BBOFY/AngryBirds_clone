package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.strategy.MissileMovingStrategyContext;

import java.util.List;

public interface IGameModel {

    void moveCannonUp();

    void moveCannonDown();

    void cannonShoot();

    void aimCannonUp();

    void aimCannonDown();

    void cannonPowerUp();

    void cannonPowerDown();

    void update();

    List<AbsMissile> getMissiles();

    List<? extends GameObject> getGameObjects();

    MissileMovingStrategyContext getMovingStrategyContext();

    void toggleShootingMode();

    Object createMemento();

    void setMemento(Object memento);

    void registerCommand(AbstractGameCommand cmd);
    void undoLastCommand();
}
