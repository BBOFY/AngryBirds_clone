package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

public class DynamicShootingMode implements IShootingMode {

    private int numOfShots = 1;

    public DynamicShootingMode() {
        GameController.incShotsEvent.addListener(this::incShots);
        GameController.decShotsEvent.addListener(this::decShots);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void shoot(AbsCannon cannon) {

        // upper missiles
        for (int i = 0; i < numOfShots / 2; ++i) {
            cannon.aimUp();
            cannon.addMissileToBatch();
        }
        for (int i = 0; i < numOfShots / 2; ++i) {
            cannon.aimDown();
        }

        // middle missile
        if (numOfShots % 2 == 1) {
            cannon.addMissileToBatch();
        }

        // lower missiles
        for (int i = 0; i < numOfShots / 2; ++i) {
            cannon.aimDown();
            cannon.addMissileToBatch();
        }
        for (int i = 0; i < numOfShots / 2; ++i) {
            cannon.aimUp();
        }

    }

    @Override
    public IShootingMode nextState() {
        return AbsCannon.SINGLE_MODE;
    }

    private void incShots() {
        if (numOfShots < MvcGameConfig.MAX_AMOUNT_SHOTS) {
            numOfShots++;
        }
    }
    private void decShots() {
        if (numOfShots > MvcGameConfig.MIN_AMOUNT_SHOTS) {
            numOfShots--;
        }
    }
}
