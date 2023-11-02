package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;

public class GameView {

    private final GameModel model;
    private final GameController controller;
    private AbstractGraphicsContextWrapper gc = new NullGraphicsContextWrapper();

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        model.cannonMovedEvent.addListener(this::onObjectMoved);
    }

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        gc.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        drawCannon();
    }

    private void drawCannon() {
        Vector2 cannonPosition = model.getCannonPos();
        gc.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannonPosition);
    }

    private void drawMissile(AbsMissile missile) {
        Vector2 missilePosition = model.getCannonPos();
        gc.drawImage(MvcGameConfig.MISSILE_IMAGE_RESOURCE, missile.getPosition());
    }

    public void setGraphicsContext(AbstractGraphicsContextWrapper gc) {
        this.gc = gc;
        render();
    }

    public void onObjectMoved() {
        drawCannon();
        render();
    }
}
