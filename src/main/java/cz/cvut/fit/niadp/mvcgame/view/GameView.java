package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.AudioPlayer;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.GameObjectsRenderer;

public class GameView {

    private final IGameModel model;
    private final GameController controller;
    private final GameObjectsRenderer renderer;

    private final AudioPlayer audioPlayer;
    private AbstractGraphicsContextWrapper gc = NullGraphicsContextWrapper.getCurr();

    public GameView(IGameModel model) {
        this.model = model;
        controller = new GameController(model);
        renderer = new GameObjectsRenderer();
        audioPlayer = new AudioPlayer();

        EventHolder.gameObjectMovedEvent.addListener(this::onObjectMoved);
        EventHolder.cannonMovedEvent.addListener(this::onCannonMoved);
        EventHolder.missileLaunchedEvent.addListener(this::onMissileLaunched);
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
