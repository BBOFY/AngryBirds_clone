package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
;

public class JavaFxVisuals implements IGameVisualsImplementor {
    private final AbstractGraphicsContextWrapper gc;

    public JavaFxVisuals(AbstractGraphicsContextWrapper gc) {
        this.gc = gc;
    }

    @Override
    public void drawImage(String imagePath, Vector2 imagePos, double angle, Vector2 size) {
        gc.drawImage(imagePath, imagePos, angle, size);
    }

    @Override
    public void drawText(String text, Vector2 pos) {
        gc.fillText(text, pos);
    }

    @Override
    public void drawLine(Vector2 from, Vector2 to) {
        gc.strokeLine(from, to);
    }

    @Override
    public void clear() {
        gc.clearRect(0, 0, MvcGameConfig.SCREEN_WIDTH, MvcGameConfig.SCREEN_HEIGHT);
    }

    @Override
    public void playSound(String audioPath, Vector2 soundOrigin) {
        gc.playSound(audioPath, soundOrigin);
    }


}
