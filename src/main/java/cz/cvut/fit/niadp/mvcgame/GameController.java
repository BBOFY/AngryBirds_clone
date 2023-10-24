package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;

import java.util.List;

public class GameController {

    private GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        Position logoPos = model.getLogoPos();
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameConfig.UP_KEY:
                    logoPos.setY(logoPos.getY() - MvcGameConfig.MOVE_STEP);
                    break;
                case MvcGameConfig.DOWN_KEY:
                    logoPos.setY(logoPos.getY() + MvcGameConfig.MOVE_STEP);
                    break;
                case MvcGameConfig.LEFT_KEY:
                    logoPos.setX(logoPos.getX() - MvcGameConfig.MOVE_STEP);
                    break;
                case MvcGameConfig.RIGHT_KEY:
                    logoPos.setX(logoPos.getX() + MvcGameConfig.MOVE_STEP);
                    break;
                case MvcGameConfig.EXIT_KEY:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
    }
}
