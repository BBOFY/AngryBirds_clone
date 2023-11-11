package cz.cvut.fit.niadp.mvcgame;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
// in the future, use Bridge to remove this dependency
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.nullPattern.AbstractGraphicsContextWrapper;
import cz.cvut.fit.niadp.mvcgame.view.GameView;

public class MvcGame {
    private GameModel model;
    private GameView view;
    private GameController controller;

    public void init() {
        this.model = GameModel.getInst();
        this.view = new GameView(model);
        this.controller = this.view.getController();
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        this.controller.processPressedKeys(pressedKeysCodes);
    }

    public String getWindowTitle() {
        return MvcGameConfig.GAME_TITLE;
    }

    public int getWindowWidth() {
        return MvcGameConfig.SCREEN_WIDTH;
    }

    public int getWindowHeight() {
        return  MvcGameConfig.SCREEN_HEIGHT;
    }

    public void setGraphicsContext(AbstractGraphicsContextWrapper gc) {
        this.view.setGraphicsContext(gc);
    }

    public void updateModel() {
        model.update();
    }
}