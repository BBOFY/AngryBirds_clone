package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private GraphicsContext gc;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        model.registerObserver(this);
    }

    public GameController getController() {
        return this.controller;
    }

    public void render() {
        gc.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        drawCannon();
    }

    private void drawCannon() {
        Position cannonPosition = model.getCannonPos();
        gc.drawImage(new Image(MvcGameConfig.CANNON_IMAGE_RESOURCE), cannonPosition.getX(), cannonPosition.getY());
    }

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
        render();
    }

    @Override
    public void update() {
        render();
    }
}
