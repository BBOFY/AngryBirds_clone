package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEventObject;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEventObject_1;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.visitor.audio.AudioPlayer;
import cz.cvut.fit.niadp.mvcgame.visitor.gui.GuiRenderer;
import cz.cvut.fit.niadp.mvcgame.visitor.renderer.GameObjectsRenderer;

public class GameView {

    private final IGameModel model;
    private final GameController controller;
    private final GameObjectsRenderer renderer;
    private final GuiRenderer guiRenderer;
    private final AudioPlayer audioPlayer;

    private IGameVisuals gv;

    public GameView(IGameModel model) {
        this.model = model;
        controller = new GameController(model);
        renderer = new GameObjectsRenderer();
        guiRenderer = new GuiRenderer();
        audioPlayer = new AudioPlayer();

        EventHolder.gameObjectMovedEvent.addListener(onObjectMovedEO);
        EventHolder.cannonMovedEvent.addListener(onCannonMovedEO);
        EventHolder.missileLaunchedEvent.addListener(onMissileLaunchedEO);
    }
    private final MyEventObject onObjectMovedEO = new MyEventObject(this::render);
    private final MyEventObject_1<AbsCannon> onCannonMovedEO = new MyEventObject_1<>(this::onCannonMoved);
    private final MyEventObject_1<AbsMissile> onMissileLaunchedEO = new MyEventObject_1<>(this::onMissileLaunched);

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        gv.clear();
        model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(renderer));
        model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(guiRenderer));
        guiRenderer.guiVisitModel(model);
        guiRenderer.renderInfo();
    }

    public void setGraphicsContext(IGameVisuals gv) {
        this.gv = gv;
        renderer.setGraphicContext(gv);
        guiRenderer.setGraphicContext(gv);
        audioPlayer.setGraphicContext(gv);
        render();
    }


    public void onCannonMoved(AbsCannon cannon) {
        cannon.acceptVisitor(audioPlayer);
    }

    public void onMissileLaunched(AbsMissile missile) {
        missile.acceptVisitor(audioPlayer);
    }

}
