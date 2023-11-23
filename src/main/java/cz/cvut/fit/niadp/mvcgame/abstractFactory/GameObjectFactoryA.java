package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.MissileA;

public class GameObjectFactoryA implements IGameObjectFactory {
    private static IGameObjectFactory inst;

    public static IGameObjectFactory getInstance() {
        if (inst == null) {
            inst = new GameObjectFactoryA();
        }
        return inst;
    }

    private GameObjectFactoryA() {}

    @Override
    public CannonA createCannon(Vector2 position) {
        return new CannonA(position, this);
    }

    @Override
    public MissileA createMissile(Vector2 position, double angle, int velocity) {
        return new MissileA(position, angle, velocity, GameModel.getInst().getMovingStrategyContext().getStrategy().clone());
    }
}
