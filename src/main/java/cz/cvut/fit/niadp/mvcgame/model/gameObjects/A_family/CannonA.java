package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableCircle;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidableAABB;

import java.util.ArrayList;
import java.util.List;

public class CannonA extends AbsCannon implements ICollidableAABB {
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
        if (angle < MvcGameConfig.MAX_CANNON_INCLINATION) {
            angle = MvcGameConfig.MAX_CANNON_INCLINATION;
        }
    }

    @Override
    public void aimUp(double angle) {
        this.angle -= angle;
        if (angle > MvcGameConfig.MAX_CANNON_INCLINATION) {
            this.angle = MvcGameConfig.MAX_CANNON_INCLINATION;
        }
    }

    @Override
    public void aimDown() {
        angle += MvcGameConfig.ANGLE_STEP;
        if (angle > MvcGameConfig.MAX_CANNON_DEPRESSION) {
            angle = MvcGameConfig.MAX_CANNON_DEPRESSION;
        }
    }
    @Override
    public void aimDown(double angle) {
        this.angle += angle;
        if (angle > MvcGameConfig.MAX_CANNON_DEPRESSION) {
            this.angle = MvcGameConfig.MAX_CANNON_DEPRESSION;
        }
    }

    @Override
    public void powerUp() {
        power = Math.min(power + MvcGameConfig.POWER_STEP, MvcGameConfig.MAX_CANNON_POWER);
    }

    @Override
    public void powerDown() {
        power = Math.max(power - MvcGameConfig.POWER_STEP, MvcGameConfig.MIN_CANNON_POWER);
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

    @Override
    public Vector2 getPos() {
        return position;
    }

    @Override
    public byte getLayer() {
        return MvcGameConfig.CANNON_LAYER_BIT;
    }

    @Override
    public byte getMask() {
        return MvcGameConfig.OBSTACLE_LAYER_BIT;
    }

    @Override
    public void react() {
        double upperPos = MvcGameConfig.CANNON_UPPER_BOUND.y + MvcGameConfig.OBSTACLE_SPRITE_SIZE.y;
        double lowerPos = MvcGameConfig.CANNON_LOWER_BOUND.y - MvcGameConfig.CANNON_SPRITE_SIZE.y;

        if (Math.abs(upperPos - position.y) < Math.abs(lowerPos - position.y)) {
            position = new Vector2(position.x, upperPos);
        }
        else {
            position = new Vector2(position.x, lowerPos);
        }

    }

    @Override
    public Vector2 getSize() {
        return MvcGameConfig.CANNON_SPRITE_SIZE;
    }

    @Override
    public CannonA clone() {
        CannonA newCannon = new CannonA(position.clone(), gameObjectFactory);
        newCannon.toRemove = toRemove;
        newCannon.angle = angle;
        newCannon.power = power;
        newCannon.isColliderEnabled = isColliderEnabled;
        newCannon.shootingMode = shootingMode;
        return newCannon;
    }
}
