package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.command.*;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.List;

public class GameController {

    private final IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameConfig.UP_KEY:
                    model.registerCommand(new MoveCannonUpCmd(model));
                    break;
                case MvcGameConfig.DOWN_KEY:
                    model.registerCommand(new MoveCannonDownCmd(model));
                    break;
                case MvcGameConfig.LEFT_KEY:
                    model.registerCommand(new AimCannonUpCmd(model));
                    break;
                case MvcGameConfig.RIGHT_KEY:
                    model.registerCommand(new AimCannonDownCmd(model));
                    break;
                case MvcGameConfig.W_KEY:
                    model.registerCommand(new CannonPowerUpCmd(model));
                    break;
                case MvcGameConfig.S_KEY:
                    model.registerCommand(new CannonPowerDownCmd(model));
                    break;
                case MvcGameConfig.SHOOT_KEY:
                    model.registerCommand(new CannonShootCmd(model));
                    break;
                case MvcGameConfig.SECONDARY_ACTION_KEY:
                    model.registerCommand(new SecondaryActionCmd(model));
                    break;
                case MvcGameConfig.TOGGLE_MOVING_STRATEGY_KEY:
                    model.registerCommand(new ToggleMovingStrategyCmd(model));
                    break;
                case MvcGameConfig.TOGGLE_SHOOTING_MODE_KEY:
                    model.registerCommand(new ToggleShootingModeCmd(model));
                    break;
                case MvcGameConfig.INC_SHOTS_KEY:
                    model.registerCommand(new IncrementShotsCmd(model));
                    break;
                case MvcGameConfig.DEC_SHOTS_KEY:
                    model.registerCommand(new DecrementShotsCmd(model));
                    break;
                case MvcGameConfig.UNDO_LAST_CMD_KEY:
                    model.registerCommand(new UndoLastCommandCmd(model));
                    break;
                case MvcGameConfig.EXIT_KEY:
                    model.registerCommand(new ExitGameCmd(model));
                    break;
                default:
                    //nothing
            }
        }
        pressedKeysCodes.clear();
    }
}
