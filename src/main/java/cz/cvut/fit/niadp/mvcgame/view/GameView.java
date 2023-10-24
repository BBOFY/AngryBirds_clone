package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.nullPattern.NullGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import javafx.scene.image.Image;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private AbstractGraphicsContextWrapper gc = new NullGraphicsContextWrapper();

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        model.registerObserver(this);
    }

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        gc.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        drawCannon();
    }

    private void drawCannon() {
        Position cannonPosition = model.getCannonPos();
        gc.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannonPosition);
    }

    public void setGraphicsContext(AbstractGraphicsContextWrapper gc) {
        this.gc = gc;
        render();
    }

    @Override
    public void update() {
        render();
    }
}
