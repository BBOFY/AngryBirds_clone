package cz.cvut.fit.niadp.mvcgame.visitor.audio;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class AudioPlayer implements IAudioVisitor {
    private IGameVisuals gv;

    public void setGraphicContext(IGameVisuals gv) {
        this.gv = gv;
    }

    @Override
    public void audioVisitCannonMove(AbsCannon cannon) {
        gv.playSound(MvcGameConfig.CANNON_MOVE_AUDIO_RESOURCE, cannon.position);
    }

    @Override
    public void audioVisitMissileShoot(AbsMissile missile) {
        gv.playSound(MvcGameConfig.CANNON_FIRE_AUDIO_RESOURCE, missile.position);

    }
}
