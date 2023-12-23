package cz.cvut.fit.niadp.mvcgame.model.gameObjects.A_family;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollidableCircle;
import cz.cvut.fit.niadp.mvcgame.visitor.collisions.ICollisionVisitor;

public class MissileA extends AbsMissile implements ICollidableCircle {
    private boolean colliderEnabled = true;

    private final IMovingStrategy movingStrategy;

    public MissileA(Vector2 initPosition, double initAngle, double initSpeed, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initSpeed);
        this.movingStrategy = movingStrategy;
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
        return MvcGameConfig.ENEMY_LAYER_BIT | MvcGameConfig.OBSTACLE_LAYER_BIT;
    }

    @Override
    public boolean isEnabled() {
        return colliderEnabled;
    }

    @Override
    public void react() {
        colliderEnabled = false;
        toRemove = true;
    }

    @Override
    public double getRadius() {
        return (MvcGameConfig.ENEMY_SPRITE_WIDTH + MvcGameConfig.ENEMY_SPRITE_HEIGHT) / 4.0;
    }
}
