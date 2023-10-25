package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.MyEvent;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Cannon;

public class GameModel {
    private final Cannon cannon;
    public final MyEvent cannonMovedEvent;

    public GameModel() {
        this.cannon = new Cannon(MvcGameConfig.INIT_CANNON_POSITION);
        this.cannonMovedEvent = new MyEvent();
    }

    public Position getCannonPos() {
        return cannon.getPosition();
    }

    public void moveCannonUp() {
        cannon.moveUp();
        cannonMovedEvent.invoke();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        cannonMovedEvent.invoke();
    }

    public void update() {
        // update missiles here
    }

}
