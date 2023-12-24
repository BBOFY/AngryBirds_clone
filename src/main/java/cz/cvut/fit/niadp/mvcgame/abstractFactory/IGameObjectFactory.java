package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.builder.IEnemyBuilder;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsObstacle;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public interface IGameObjectFactory {
    void init(IGameModel model);
    AbsCannon createCannon(Vector2 position);
    AbsMissile createMissile(Vector2 position, double angle, double velocity);
    AbsMissile createMissile(Vector2 position, double angle, double velocity, IMovingStrategy strategy);
    IEnemyBuilder createEnemyBuilder();
    AbsObstacle createObstacle(Vector2 position, Vector2 velocity);
}
