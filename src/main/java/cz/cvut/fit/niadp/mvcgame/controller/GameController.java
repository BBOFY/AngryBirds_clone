package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;

import java.util.List;

public class GameController {

    private final GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameConfig.UP_KEY:
                    model.moveCannonUp();
                    break;
                case MvcGameConfig.DOWN_KEY:
                    model.moveCannonDown();
                    break;
                case MvcGameConfig.LEFT_KEY:
                    model.aimCannonUp();
                    break;
                case MvcGameConfig.RIGHT_KEY:
                    model.aimCannonDown();
                    break;
                case MvcGameConfig.W_KEY:
                    model.cannonPowerUp();
                    break;
                case MvcGameConfig.S_KEY:
                    model.cannonPowerDown();
                    break;
                case MvcGameConfig.SHOOT_KEY:
                    model.cannonShoot();
                    break;
                case MvcGameConfig.TOGGLE_MOVING_STRATEGY_KEY:
                    model.toggleMovingStrategy();
                    break;
                case MvcGameConfig.TOGGLE_SHOOTING_MODE_KEY:
                    model.toggleShootingMode();
                    break;
                case MvcGameConfig.SAVE_SNAPSHOT_KEY:
                    CareTaker.createMemento();
                    break;
                case MvcGameConfig.LOAD_SNAPSHOT_KEY:
                    CareTaker.setMemento();
                    break;
                case MvcGameConfig.EXIT_KEY:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
        pressedKeysCodes.clear();
    }
}
