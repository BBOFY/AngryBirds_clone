package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public class GameVisuals implements IGameVisuals {

    private final IGameVisualsImplementor implementor;

    public GameVisuals(IGameVisualsImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void drawImage(String path, Vector2 position, double angle, Vector2 scale) {
        implementor.drawImage(path, position, angle, scale);
    }

    @Override
    public void drawText(String text, Vector2 position) {
        implementor.drawText(text, position);
    }

    @Override
    public void drawRectangle(Vector2 from, Vector2 to) {
        this.implementor.drawLine(from, new Vector2(to.x, from.y));
        this.implementor.drawLine(new Vector2(to.x, from.y), to);
        this.implementor.drawLine(to, new Vector2(from.x, to.y));
        this.implementor.drawLine(new Vector2(from.x, to.y), to);
    }

    @Override
    public void clear() {
        implementor.clear();
    }

    @Override
    public void playSound(String audioPath, Vector2 soundOrigin) {
        implementor.playSound(audioPath, soundOrigin);
    }
}
