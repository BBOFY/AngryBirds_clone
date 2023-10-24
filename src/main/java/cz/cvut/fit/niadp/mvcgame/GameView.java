package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameView {

    private GameModel model;
    private GameController controller;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
    }

    public GameController getController() {
        return this.controller;
    }

    public void render(GraphicsContext gr) {
        Position logoPos = model.getLogoPos();
        gr.drawImage(new Image(MvcGameConfig.LOGO_IMAGE_PATH), logoPos.getX(), logoPos.getY());
    }
}
