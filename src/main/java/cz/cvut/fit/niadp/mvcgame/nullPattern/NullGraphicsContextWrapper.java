package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.model.Vector2;

public final class NullGraphicsContextWrapper extends AbstractGraphicsContextWrapper {

    public static NullGraphicsContextWrapper curr;

    public static NullGraphicsContextWrapper getCurr() {
        if (curr == null) {
            curr = new NullGraphicsContextWrapper();
        }
        return curr;
    }

    private NullGraphicsContextWrapper() {}

    @Override
    public void clearRect(double x, double y, double w, double h) {
        System.err.println("Method clearRect is not implemented.");
    }

    @Override
    public void drawImage(String imagePath, Vector2 imagePosition) {
        System.err.println("Method drawImage is not implemented.");
    }
}
