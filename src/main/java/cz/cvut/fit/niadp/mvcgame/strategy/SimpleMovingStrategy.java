package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class SimpleMovingStrategy implements IMovingStrategy {
    @Override
    public void updatePosition(AbsMissile missile) {
        double initSpeed = missile.getInitSpeed();
        double initAngle = missile.getInitAngle();

        double dX = (initSpeed * Math.cos(initAngle));
        double dY = (initSpeed * Math.sin(initAngle));

        missile.move(new Vector2(dX, dY));
    }

    @Override
    public SimpleMovingStrategy clone() {
        return new SimpleMovingStrategy();
    }

    @Override
    public String getName() {
        return "Simple";
    }
}
