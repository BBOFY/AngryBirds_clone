package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.MvcGame;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GraphicsContextWrapper extends AbstractGraphicsContextWrapper {
    private final GraphicsContext gc;

    private final Scene theScene;

    public GraphicsContextWrapper(MvcGame theMvcGame, Stage stage) {
        String winTitle = theMvcGame.getWindowTitle();
        int winWidth = theMvcGame.getWindowWidth();
        int winHeight = theMvcGame.getWindowHeight();
        stage.setTitle( winTitle );
        Group root = new Group();
        theScene = new Scene( root );
        stage.setScene( theScene );
        Canvas canvas = new Canvas( winWidth, winHeight );
        root.getChildren().add( canvas );
        this.gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void clearRect(double x, double y, double w, double h) {
        gc.clearRect(x, y, w, h);
    }

    @Override
    public void drawImage(String imagePath, Position imagePosition) {
        gc.drawImage(new Image(imagePath), imagePosition.getX(), imagePosition.getY());
    }

    public Scene getScene() {
        return theScene;
    }


}
