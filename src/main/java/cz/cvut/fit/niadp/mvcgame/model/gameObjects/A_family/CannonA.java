package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.ArrayList;
import java.util.List;

public class CannonA extends AbsCannon {

    private final IGameObjectFactory gameObjectFactory;

    private final List<AbsMissile> missilesBatch = new ArrayList<>();

    public CannonA(Vector2 initPosition, IGameObjectFactory gameObjectFactory) {
        this.position = initPosition;
        this.gameObjectFactory = gameObjectFactory;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;

        this.shootingMode = SINGLE_MODE;
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
    public void aimUp(double angle) {
        this.angle -= angle;
    }

    @Override
    public void aimDown() {
        angle += MvcGameConfig.ANGLE_STEP;
    }
    @Override
    public void aimDown(double angle) {
        this.angle += angle;
    }

    @Override
    public void powerUp() {
        power = Math.min(power += MvcGameConfig.POWER_STEP, MvcGameConfig.MAX_CANNON_POWER);
    }

    @Override
    public void powerDown() {
        power = Math.max(power -= MvcGameConfig.POWER_STEP, MvcGameConfig.MIN_CANNON_POWER);
    }

    @Override
    public void addMissileToBatch() {
        missilesBatch.add(gameObjectFactory.createMissile(position.clone(), angle, power));
    }

    @Override
    public List<AbsMissile> shoot() {
        missilesBatch.clear();
        shootingMode.shoot(this);
        return missilesBatch;
    }

    @Override
    public void toggleShootingMode() {
        shootingMode = shootingMode.nextState();
    }

}
