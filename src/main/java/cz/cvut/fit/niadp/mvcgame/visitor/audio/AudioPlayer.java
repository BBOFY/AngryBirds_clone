package cz.cvut.fit.niadp.mvcgame.visitor.audio;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer implements IAudioVisitor {
    private AbstractGraphicsContextWrapper gc = NullGraphicsContextWrapper.getCurr();

    public void setGraphicContext(AbstractGraphicsContextWrapper gc) {
        this.gc = gc;
    }

    @Override
    public void audioVisitCannonMove(AbsCannon cannon) {
        gc.playSound(MvcGameConfig.CANNON_MOVE_AUDIO_RESOURCE, cannon.position);
        Media media = new Media(getClass().getResource(MvcGameConfig.CANNON_MOVE_AUDIO_RESOURCE).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void audioVisitMissileShoot(AbsMissile missile) {
        gc.playSound(MvcGameConfig.CANNON_FIRE_AUDIO_RESOURCE, missile.position);

    }
}
