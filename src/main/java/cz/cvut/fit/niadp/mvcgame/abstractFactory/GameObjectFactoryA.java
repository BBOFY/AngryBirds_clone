package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family.MissileA;

public class GameObjectFactoryA implements IGameObjectFactory {

    private GameModel model;

    public GameObjectFactoryA(GameModel model) {
        this.model = model;
    }

    @Override
    public CannonA createCannon(Vector2 position) {
        return new CannonA(position, this);
    }

    @Override
    public MissileA createMissile(Vector2 position) {
        return new MissileA(model.getCannonPos().clone());
    }
}