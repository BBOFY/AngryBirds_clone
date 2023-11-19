package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class CannonA extends AbsCannon {

    private final IGameObjectFactory gameObjectFactory;

    public CannonA(Vector2 initPosition, IGameObjectFactory gameObjectFactory) {
        this.position = initPosition;
        this.gameObjectFactory = gameObjectFactory;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
    }

    @Override
    public void moveUp() {
        this.move(new Vector2(0, -MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        this.move(new Vector2(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void aimUp() {
        angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        power += MvcGameConfig.POWER_STEP;
    }

    @Override
    public void powerDown() {
        power -= MvcGameConfig.POWER_STEP;
    }

    @Override
    public AbsMissile shoot() {
        return gameObjectFactory.createMissile(position.clone(), angle, power);
    }

}
