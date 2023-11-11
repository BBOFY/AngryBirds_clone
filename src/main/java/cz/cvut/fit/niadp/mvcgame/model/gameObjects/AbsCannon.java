package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public abstract class AbsCannon extends GameObject implements IAudioVisitable {

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract AbsMissile shoot();

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitCannon(this);
    }

    @Override
    public void acceptVisitor(IAudioVisitor visitor) {
        visitor.audioVisitCannonMove(this);
    }
}
