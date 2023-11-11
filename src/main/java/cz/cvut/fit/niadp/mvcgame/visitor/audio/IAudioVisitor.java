package cz.cvut.fit.niadp.mvcgame.visitor.audio;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public interface IAudioVisitor {
    void audioVisitCannonMove(AbsCannon cannon);
    void audioVisitMissileShoot(AbsMissile missile);
}
