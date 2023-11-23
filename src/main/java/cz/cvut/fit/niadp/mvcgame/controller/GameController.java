package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;

import java.util.List;

public class GameController {

    public static MyEvent secondaryActionEvent = new MyEvent();

    private static GameController curr;
    private final GameModel model;

    private GameController() {
        this.model = GameModel.getInst();
    }

    public static GameController getInst() {
        if (curr == null) {
            curr = new GameController();
        }
        return curr;
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
                case MvcGameConfig.SECONDARY_ACTION_KEY:
                    secondaryActionEvent.invoke();
                    break;
                case MvcGameConfig.TOGGLE_MOVING_STRATEGY_KEY:
                    model.getMovingStrategyContext().toggleMovingStrategy();
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
