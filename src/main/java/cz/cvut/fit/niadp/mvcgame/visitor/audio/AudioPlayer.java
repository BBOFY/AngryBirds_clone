package cz.cvut.fit.niadp.mvcgame.visitor.audio;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEventObject;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import jdk.jfr.Event;

public class AudioPlayer implements IAudioVisitor {
    private IGameVisuals gv;

    public AudioPlayer() {
        EventHolder.missileHitEvent.addListener(eo);
    }

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

    private MyEventObject eo = new MyEventObject(this::onMissileHit);
    private void onMissileHit() {
        gv.playSound(MvcGameConfig.MISSILE_HIT_AUDIO_RESOURCE, Vector2.ZERO);
    }
}
