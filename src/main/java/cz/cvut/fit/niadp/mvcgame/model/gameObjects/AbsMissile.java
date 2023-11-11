package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitable;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.IAudioVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.IGameObjectsVisitor;

public abstract class AbsMissile extends GameObject implements IAudioVisitable {

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.renderVisitMissile(this);
    }

    @Override
    public void acceptVisitor(IAudioVisitor visitor) {
        visitor.audioVisitMissileShoot(this);
    }
}
