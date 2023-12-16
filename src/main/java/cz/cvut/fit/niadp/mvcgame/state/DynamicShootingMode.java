package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

public class DynamicShootingMode implements IShootingMode {

    private int numOfShots = 1;

    private final EventObject incShotsEO = new EventObject(this::incShots);
    private final EventObject decShotsEO = new EventObject(this::decShots);

    public DynamicShootingMode() {
        EventHolder.incShotsEvent.addListener(incShotsEO);
        EventHolder.decShotsEvent.addListener(decShotsEO);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void shoot(AbsCannon cannon) {

        // Aim cannon all the way up
        for (int i = 0; i < numOfShots/2; ++i) {
            cannon.aimUp();
        }
        // Correction for even number of missiles to fill the gap
        if (numOfShots % 2 == 0) {
            cannon.aimDown(MvcGameConfig.ANGLE_STEP/2.0);
        }
        // Aim down while shooting
        for (int i = 0; i < numOfShots; ++i) {
            cannon.addMissileToBatch();
            cannon.aimDown();
        }
        // Restore to the starting cannon angle
        for (int i = 0; i < numOfShots/2; ++i) {
            cannon.aimUp();
        }
        if (numOfShots % 2 == 0) {
            cannon.aimDown(MvcGameConfig.ANGLE_STEP/2.0);
        }
        cannon.aimUp();

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
