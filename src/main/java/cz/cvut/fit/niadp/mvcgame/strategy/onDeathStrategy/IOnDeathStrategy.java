package cz.cvut.fit.niadp.mvcgame.strategy.onDeathStrategy;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;

public interface IOnDeathStrategy {
    void doSomething(Enemy enemy);
}
