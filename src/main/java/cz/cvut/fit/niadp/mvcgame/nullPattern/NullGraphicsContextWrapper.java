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
        System.err.println("Method clearRect is not implemented. Check graphics context initialization.");
    }

    @Override
    public void drawImage(String imagePath, Vector2 imagePosition) {
        System.err.println("Method drawImage is not implemented. Check graphics context initialization.");
    }

    @Override
    public void drawImage(String imagePath, Vector2 imagePosition, double angle) {
        System.err.println("Method drawImage is not implemented. Check graphics context initialization.");
    }

    @Override
    public void playSound(String audioPath, Vector2 soundOrigin) {
        System.err.println("Method playSound is not implemented. Check graphics context initialization.");
    }

    @Override
    public void fillText(String text, Vector2 pos) {
        System.err.println("Method fillText is not implemented. Check graphics context initialization.");
    }

    @Override
    public void strokeLine(Vector2 from, Vector2 to) {
        System.err.println("Method strokeLine is not implemented. Check graphics context initialization.");
    }
}
