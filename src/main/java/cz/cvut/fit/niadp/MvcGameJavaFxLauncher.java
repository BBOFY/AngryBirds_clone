package cz.cvut.fit.niadp;

import cz.cvut.fit.niadp.mvcgame.nullPattern.GraphicsContextWrapper;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import cz.cvut.fit.niadp.mvcgame.MvcGame;

public class MvcGameJavaFxLauncher extends Application {

    private static final MvcGame theMvcGame = new MvcGame();

    @Override
    public void init() {
        theMvcGame.init();
    }

    @Override
    public void start(Stage stage) {
        GraphicsContextWrapper gc = new GraphicsContextWrapper(
                theMvcGame,
                stage
        );

        Scene theScene = gc.getScene();

        ArrayList<String> pressedKeysCodes = new ArrayList<>();
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    // only add once... prevent duplicates
                    if (!pressedKeysCodes.contains(code)) {
                        pressedKeysCodes.add(code);
                    }
                }
        );
        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    pressedKeysCodes.remove( code );
                }
        );

        theMvcGame.setGraphicsContext(gc);
        // the game-loop
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                theMvcGame.processPressedKeys(pressedKeysCodes);
                theMvcGame.updateModel();
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}