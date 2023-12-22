package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface IGameVisuals {

    void drawImage(String path, Vector2 position, double angle, Vector2 scale);
    void drawText(String text, Vector2 position);
    void drawRectangle(Vector2 from, Vector2 to);
    void clear();

    void playSound(String audioPath, Vector2 soundOrigin);

}
