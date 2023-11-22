package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

public class SingleShotMode implements IShootingMode {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void shoot(AbsCannon cannon) {
        cannon.addMissileToBatch();
    }

    @Override
    public IShootingMode nextState() {
        return AbsCannon.DOUBLE_MODE;
    }
}
