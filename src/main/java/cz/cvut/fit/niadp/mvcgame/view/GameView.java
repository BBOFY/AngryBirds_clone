package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.AudioPlayer;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.GameObjectsRenderer;

public class GameView {

    private final GameModel model;
    private final GameController controller;
    private final GameObjectsRenderer renderer;

    private final AudioPlayer audioPlayer;
    private AbstractGraphicsContextWrapper gc = NullGraphicsContextWrapper.getCurr();

    private static GameView curr;

    public GameView() {
        model = GameModel.getInst();
        controller = GameController.getInst();
        renderer = new GameObjectsRenderer();
        audioPlayer = new AudioPlayer();

        model.gameObjectMovedEvent.addListener(this::onObjectMoved);
        model.cannonMovedEvent.addListener(this::onCannonMoved);
        model.missileLaunchedEvent.addListener(this::onMissileLaunched);
    }

    public static GameView getInst() {
        if (curr == null) {
            curr = new GameView();
        }
        return curr;
    }

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        gc.clearRect(0, 0, MvcGameConfig.SCREEN_WIDTH, MvcGameConfig.SCREEN_HEIGHT);
        model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(renderer));
    }

    public void setGraphicsContext(AbstractGraphicsContextWrapper gc) {
        this.gc = gc;
        renderer.setGraphicContext(gc);
        audioPlayer.setGraphicContext(gc);
        render();
    }

    public void onObjectMoved() {
        render();
    }

    public void onCannonMoved(AbsCannon cannon) {
        cannon.acceptVisitor(audioPlayer);
    }

    public void onMissileLaunched(AbsMissile missile) {
        missile.acceptVisitor(audioPlayer);
    }

}
