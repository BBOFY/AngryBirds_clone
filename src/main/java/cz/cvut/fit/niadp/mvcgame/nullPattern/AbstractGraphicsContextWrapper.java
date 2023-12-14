package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public abstract class AbstractGraphicsContextWrapper {
    public abstract void clearRect(double x, double y, double w, double h);
    public abstract void drawImage(String imagePath, Vector2 imagePosition);
    public abstract void drawImage(String imagePath, Vector2 imagePosition, double angle);
    public abstract void playSound(String audioPath, Vector2 soundOrigin);
    public abstract void fillText(String text, Vector2 pos);
    public abstract void strokeLine(Vector2 from, Vector2 to);
}
