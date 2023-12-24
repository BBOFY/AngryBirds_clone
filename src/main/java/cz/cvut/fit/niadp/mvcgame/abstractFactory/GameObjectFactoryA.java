package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.builder.IEnemyBuilder;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.EnemyBuilderA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.MissileA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.ObstacleA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsObstacle;
import cz.cvut.fit.niadp.mvcgame.strategy.movingStrategy.IMovingStrategy;

public class GameObjectFactoryA implements IGameObjectFactory {
    private static IGameObjectFactory inst;

    private IGameModel model;

    public static IGameObjectFactory getInstance() {
        if (inst == null) {
            inst = new GameObjectFactoryA();
        }
        return inst;
    }

    public void init(IGameModel model) {
        this.model = model;
    }

    private GameObjectFactoryA() {}

    @Override
    public CannonA createCannon(Vector2 position) {
        return new CannonA(position, this);
    }

    @Override
    public MissileA createMissile(Vector2 position, double angle, double velocity) {
        return new MissileA(position, angle, velocity, model.getMovingStrategyContext().getStrategy().clone());
    }

    @Override
    public MissileA createMissile(Vector2 position, double angle, double velocity, IMovingStrategy strategy) {
        return new MissileA(position, angle, velocity, strategy.clone());
    }

    @Override
    public IEnemyBuilder createEnemyBuilder() {
        return new EnemyBuilderA();
    }

    @Override
    public AbsObstacle createObstacle(Vector2 position, Vector2 velocity) {
        return new ObstacleA(position, velocity);
    }
}
