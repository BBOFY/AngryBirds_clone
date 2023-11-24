package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

public class DoubleShotMode implements IShootingMode {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void shoot(AbsCannon cannon) {
        cannon.aimUp();
        cannon.addMissileToBatch();
        cannon.aimDown();
        cannon.aimDown();
        cannon.addMissileToBatch();
        cannon.aimUp();
    }

    @Override
    public IShootingMode nextState() {
        return AbsCannon.DYNAMIC_MODE;
    }
}
