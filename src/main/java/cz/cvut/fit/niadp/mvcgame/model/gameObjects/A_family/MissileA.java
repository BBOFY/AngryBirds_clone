package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.ICollidableCircle;

public class MissileA extends AbsMissile implements ICollidableCircle {
    private final Vector2 colliderCenter;
    private final double colliderRadius;
    private boolean colliderEnabled = true;

    private final IMovingStrategy movingStrategy;

    public MissileA(Vector2 initPosition, double initAngle, double initSpeed, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initSpeed);
        this.movingStrategy = movingStrategy;
        colliderCenter = new Vector2(
                position.x + MvcGameConfig.ENEMY_SPRITE_WIDTH / 2.0,
                position.y + MvcGameConfig.ENEMY_SPRITE_HEIGHT / 2.0
        );
        colliderRadius = (MvcGameConfig.ENEMY_SPRITE_WIDTH + MvcGameConfig.ENEMY_SPRITE_HEIGHT) / 4.0;
    }

    @Override
    public void move() {
        movingStrategy.updatePosition(this);
    }

    @Override
    public Vector2 getPos() {
        return position;
    }

    @Override
    public byte getLayer() {
        return MvcGameConfig.MISSILE_LAYER_BIT;
    }

    @Override
    public byte getMask() {
        return MvcGameConfig.ENEMY_LAYER_BIT /*| MvcGameConfig.OBSTACLE_LAYER_BIT*/;
    }

    @Override
    public boolean isColliderEnabled() {
        return colliderEnabled;
    }

    @Override
    public void react() {
        colliderEnabled = false;
        toRemove = true;
    }

    @Override
    public double getRadius() {
        return colliderRadius;
    }

    @Override
    public Vector2 getCenter() {
        return colliderCenter;
    }
}
