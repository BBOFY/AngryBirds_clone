package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class RealisticMovingStrategy implements IMovingStrategy {
    @Override
    public void updatePosition(AbsMissile missile) {
        double initSpeed = missile.getInitSpeed();
        double initAngle = missile.getInitAngle();
        double time = missile.getAge() / 100;

        double dX = (initSpeed * Math.cos(initAngle));
        double dY = (initSpeed * Math.sin(initAngle) + (0.5 * MvcGameConfig.GRAVITY * Math.pow(time, 2)));

        missile.move(new Vector2(dX, dY));
    }

    @Override
    public RealisticMovingStrategy clone() {
        return new RealisticMovingStrategy();
    }

    @Override
    public String getName() {
        return "Realistic";
    }
}
