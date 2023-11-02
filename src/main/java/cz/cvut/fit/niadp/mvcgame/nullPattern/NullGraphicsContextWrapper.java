package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public class NullGraphicsContextWrapper extends AbstractGraphicsContextWrapper {
    @Override
    public void clearRect(double x, double y, double w, double h) {
        System.err.println("Method clearRect is not implemented.");
    }

    @Override
    public void drawImage(String imagePath, Vector2 imagePosition) {
        System.err.println("Method drawImage is not implemented.");
    }
}
