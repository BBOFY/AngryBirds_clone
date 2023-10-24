package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.model.Position;

public abstract class AbstractGraphicsContextWrapper {
    public abstract void clearRect(double x, double y, double w, double h);
    public abstract void drawImage(String imagePath, Position imagePosition);


}