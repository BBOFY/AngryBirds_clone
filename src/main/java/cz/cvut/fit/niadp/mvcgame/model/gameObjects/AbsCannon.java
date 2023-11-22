package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.state.DoubleShotMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShotMode;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject implements IAudioVisitable {

    protected IShootingMode shootingMode;
    public static IShootingMode SINGLE_MODE = new SingleShotMode();
    public static IShootingMode DOUBLE_MODE = new DoubleShotMode();

    protected int power;
    protected double angle;

    public abstract void moveUp();
    public abstract void moveDown();

    public abstract void aimUp();
    public abstract void aimDown();

    public abstract void powerUp();
    public abstract void powerDown();

    public abstract void addMissileToBatch();
    public abstract List<AbsMissile> shoot();

    public abstract void toggleShootingMode();

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitCannon(this);
    }

    @Override
    public void acceptVisitor(IAudioVisitor visitor) {
        visitor.audioVisitCannonMove(this);
    }
}
