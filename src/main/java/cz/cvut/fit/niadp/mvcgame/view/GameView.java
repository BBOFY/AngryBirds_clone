package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.AudioPlayer;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.GameObjectsRenderer;

public class GameView {

    private final IGameModel model;
    private final GameController controller;
    private final GameObjectsRenderer renderer;

    private final AudioPlayer audioPlayer;
    private IGameVisuals gv;

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
        gv.clear();
        model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(renderer));
    }

    public void setGraphicsContext(IGameVisuals gv) {
        this.gv = gv;
        renderer.setGraphicContext(gv);
        audioPlayer.setGraphicContext(gv);
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
