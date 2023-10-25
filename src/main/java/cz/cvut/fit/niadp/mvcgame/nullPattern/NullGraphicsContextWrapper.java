package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.Scene;

public class NullGraphicsContextWrapper extends AbstractGraphicsContextWrapper {
    @Override
    public void clearRect(double x, double y, double w, double h) {
        System.out.println("Method clearRect is not implemented.");
    }

    @Override
    public void drawImage(String imagePath, Position imagePosition) {
        System.out.println("Method drawImage is not implemented.");
    }

    @Override
    public Scene getScene() {
        System.out.println("There is no scene initialized.");
        return null;
    }
}
