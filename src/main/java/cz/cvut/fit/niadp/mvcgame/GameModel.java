package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;

public class GameModel {
    private Position logoPos;

    public GameModel() {
        this.logoPos = new Position(
                ((MvcGameConfig.MAX_X/2)-128),
                ((MvcGameConfig.MAX_Y/2)-128)
        );
    }

    public Position getLogoPos() {
        return logoPos;
    }
}
