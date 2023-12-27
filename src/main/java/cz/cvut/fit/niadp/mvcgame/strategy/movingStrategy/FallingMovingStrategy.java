package cz.cvut.fit.niadp.mvcgame.strategy.movingStrategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEventObject;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FallingMovingStrategy implements IMovingStrategy {

    private boolean triggered = false;
    private LocalDateTime triggerTime;

    private final MyEventObject triggerFunctionalityEO = new MyEventObject(this::triggerFunctionality);
    private void triggerFunctionality() {
        if (triggered) {
            return;
        }
        triggered = true;
        triggerTime = LocalDateTime.now();
    }

    @Override
    public void updatePosition(AbsMissile missile) {

        if (!triggered) {
            double dX = (missile.getInitSpeed() * Math.cos(missile.getInitAngle()));
            double dY = (missile.getInitSpeed() * Math.sin(missile.getInitAngle()));

            missile.move(new Vector2(dX, dY));
        }
        else {
            double time = (ChronoUnit.MILLIS.between(triggerTime, LocalDateTime.now()) + 10) / 100.0;
            double dY = (0.5 * MvcGameConfig.GRAVITY * Math.pow(time, 2));

            missile.move(new Vector2(0, dY));
        }

    }

    @Override
    public FallingMovingStrategy clone() {
        FallingMovingStrategy newStrategy = new FallingMovingStrategy();
        newStrategy.triggered = triggered;
        newStrategy.triggerTime = triggerTime;
        EventHolder.secondaryActionEvent.addListener(newStrategy.triggerFunctionalityEO);

        return newStrategy;
    }

    @Override
    public String getName() {
        return "Falling";
    }
}
