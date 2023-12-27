package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.prototype.ICloneable;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShotMode;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShotMode;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitor;
import cz.cvut.fit.niadp.mvcgame.chain.collisions.colliders.ICollidable;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.IGuiVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject implements IAudioVisitable, ICollidable, ICloneable<AbsCannon> {

    protected boolean isColliderEnabled = true;
    protected IShootingMode shootingMode;
    public static IShootingMode SINGLE_MODE = new SingleShotMode();
    public static IShootingMode DOUBLE_MODE = new DoubleShotMode();
    public static IShootingMode DYNAMIC_MODE = new DynamicShootingMode();

    protected int power;
    protected double angle;

    public abstract void moveUp();
    public abstract void moveDown();

    public abstract void aimUp();
    public abstract void aimUp(double angle);
    public abstract void aimDown();
    public abstract void aimDown(double angle);

    public abstract void powerUp();
    public abstract void powerDown();

    public abstract void addMissileToBatch();
    public abstract List<AbsMissile> shoot();

    public abstract void toggleShootingMode();

    public double getAngle() {
        return angle;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitCannon(this);
    }

    @Override
    public void acceptVisitor(IAudioVisitor visitor) {
        visitor.audioVisitCannonMove(this);
    }

    @Override
    public void acceptVisitor(IGuiVisitor visitor) {
        visitor.guiVisitCannon(this, shootingMode);
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean isColliderEnabled() {
        return isColliderEnabled;
    }

    @Override
    public abstract AbsCannon clone();
}
