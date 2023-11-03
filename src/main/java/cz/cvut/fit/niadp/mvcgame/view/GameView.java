package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.visitor.GameObjectsRenderer;

public class GameView {

    private final GameModel model;
    private final GameController controller;
    private final GameObjectsRenderer renderer;
    private AbstractGraphicsContextWrapper gc = NullGraphicsContextWrapper.getCurr();

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        model.gameObjectMovedEvent.addListener(this::onObjectMoved);
        this.renderer = new GameObjectsRenderer();
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
        render();
    }

    public void onObjectMoved() {
        render();
    }
}
