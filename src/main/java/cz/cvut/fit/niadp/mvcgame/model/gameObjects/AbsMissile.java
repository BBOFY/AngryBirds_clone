package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject implements IAudioVisitable {

    private double initAngle;
    private int initVelocity;

    protected AbsMissile(Vector2 initPosition, double initAngle, int initVelocity) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    public abstract void move();

    public double getInitAngle() {
        return initAngle;
    }

    public int getInitVelocity() {
        return initVelocity;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitMissile(this);
    }

    @Override
    public void acceptVisitor(IAudioVisitor visitor) {
        visitor.audioVisitMissileShoot(this);
    }
}
