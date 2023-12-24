package cz.cvut.fit.niadp.mvcgame.strategy.onDeathStrategy;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;

public class CreateObstacleDeathStrategy implements IOnDeathStrategy {
    @Override
    public void doSomething(Enemy enemy) {
        EventHolder.addObstacleEvent.invoke(
                GameObjectFactoryA.getInstance().createObstacle(enemy.position.clone(), Vector2.ZERO)
        );
    }
}
