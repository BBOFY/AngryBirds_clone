package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public interface IGameVisualsImplementor {

    void drawImage(String imagePath, Vector2 imagePosition);
    void drawImage(String imagePath, Vector2 imagePosition, double angle);
    void drawText(String text, Vector2 position);
    void drawLine(Vector2 from, Vector2 to);
    void clear();

    void playSound(String audioPath, Vector2 soundOrigin);
}
