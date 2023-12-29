package cz.cvut.fit.niadp.mvcgame;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.proxy.GameModelProxy;
import cz.cvut.fit.niadp.mvcgame.view.GameView;

public class MvcGame {

    private IGameModel model;
    private GameView view;
    private GameController controller;

    public void init() {
        this.model = new GameModelProxy(new GameModel());
        this.view = new GameView(model);
        this.controller = view.getController();
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

    public void setGraphicsContext(IGameVisuals gv) {
        this.view.setGraphicsContext(gv);
    }

    public void updateModel() {
        model.update();
    }
}