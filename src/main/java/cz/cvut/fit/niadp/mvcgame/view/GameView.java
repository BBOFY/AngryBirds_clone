package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventObject;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventObject_1;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
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

        EventHolder.gameObjectMovedEvent.addListener(onObjectMovedEO);
        EventHolder.cannonMovedEvent.addListener(onCannonMovedEO);
        EventHolder.missileLaunchedEvent.addListener(onMissileLaunchedEO);
    }
    
    private final EventObject onObjectMovedEO = new EventObject(this::render);
    private final EventObject_1<AbsCannon> onCannonMovedEO = new EventObject_1<>(this::onCannonMoved);
    private final EventObject_1<AbsMissile> onMissileLaunchedEO = new EventObject_1<>(this::onMissileLaunched);

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        gv.clear();
        model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(renderer));

        gv.drawText("Hello", new Vector2(1, 1));
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
