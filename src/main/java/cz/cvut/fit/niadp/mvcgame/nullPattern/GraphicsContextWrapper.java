package cz.cvut.fit.niadp.mvcgame.nullPattern;

import cz.cvut.fit.niadp.mvcgame.MvcGame;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
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
        this.theScene = new Scene(root);
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
    public void drawImage(String imagePath, Vector2 imagePosition) {
        gc.drawImage(new Image(imagePath), imagePosition.x, imagePosition.y);
    }

    @Override
    public void drawImage(String imagePath, Vector2 imagePosition, double angle) {
        Image image = new Image(imagePath);
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, Math.toDegrees(angle), imagePosition.x + image.getWidth() / 2, imagePosition.y + image.getHeight() / 2);
        gc.drawImage(image, imagePosition.x, imagePosition.y);
        gc.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Sound origin is not used. It could be used in 3D game as the origin of the sound.
     */
    @Override
    public void playSound(String audioPath, Vector2 soundOrigin) {
        Media media = new Media(getClass().getResource(audioPath).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public Scene getScene() {
        return theScene;
    }


}
