package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectFactoryA;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventObject;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.time.LocalDateTime;

public class SplitMovingStrategy implements IMovingStrategy {

    private boolean triggered = false;

    private AbsMissile missile;

    private final EventObject triggerFunctionalityEO = new EventObject(this::triggerFunctionality);
    private void triggerFunctionality() {

        if (triggered) {
            return;
        }
        triggered = true;

        var missileUp = GameObjectFactoryA.getInstance().createMissile(
            missile.position.clone(),
            missile.getInitAngle() + MvcGameConfig.ANGLE_STEP,
            missile.getInitSpeed(),
            this
            );
        var missileDown = GameObjectFactoryA.getInstance().createMissile(
            missile.position.clone(),
            missile.getInitAngle() - MvcGameConfig.ANGLE_STEP,
            missile.getInitSpeed(),
            this
        );
        EventHolder.addMissileEvent.invoke(missileUp);
        EventHolder.addMissileEvent.invoke(missileDown);
    }

    @Override
    public void updatePosition(AbsMissile missile) {
        this.missile = missile;
        double initSpeed = missile.getInitSpeed();
        double initAngle = missile.getInitAngle();

        double dX = (initSpeed * Math.cos(initAngle));
        double dY = (initSpeed * Math.sin(initAngle));

        missile.move(new Vector2(dX, dY));

    }

    @Override
    public SplitMovingStrategy clone() {
        SplitMovingStrategy newStrategy = new SplitMovingStrategy();
        newStrategy.triggered = triggered;
        EventHolder.secondaryActionEvent.addListener(newStrategy.triggerFunctionalityEO);

        return newStrategy;
    }

}
