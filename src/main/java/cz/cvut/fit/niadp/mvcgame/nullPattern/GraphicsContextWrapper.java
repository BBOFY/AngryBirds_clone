package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GraphicsContextWrapper extends AbstractGraphicsContextWrapper {
    private final GraphicsContext gc;

    public GraphicsContextWrapper(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void clearRect(double x, double y, double w, double h) {
        gc.clearRect(x, y, w, h);
    }

    @Override
    public void drawImage(String imagePath, Position imagePosition) {
        gc.drawImage(new Image(imagePath), imagePosition.getX(), imagePosition.getY());
    }


}
