package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbsMissile {

    private IMovingStrategy movingStrategy;

    public MissileA(Vector2 initPosition, double initAngle, double initSpeed, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initSpeed);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public void move() {
        movingStrategy.updatePosition(this);
    }
}
