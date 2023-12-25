package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import org.junit.Assert;
import org.junit.Test;


public class CareTakerTests {

    GameModel model;

    @Test
    public void createMementoTest() {
        model = new GameModel();
        CareTaker.getInstance().setModel(model);

        Vector2 startingPos = model.getCannon().position.clone();
        double startingAngle = model.getCannon().getAngle();

        CareTaker.getInstance().createMemento();
        Assert.assertEquals(startingPos.y, model.getCannon().position.y, 0);
        Assert.assertEquals(startingAngle, model.getCannon().getAngle(), 0);

        model.moveCannonDown();
        Assert.assertEquals(startingPos.y + MvcGameConfig.MOVE_STEP, model.getCannon().position.y, 0);
        Assert.assertEquals(startingAngle, model.getCannon().getAngle(), 0);

        CareTaker.getInstance().setMemento();
        Assert.assertEquals(startingPos.y, model.getCannon().position.y, 0);
        Assert.assertEquals(startingAngle, model.getCannon().getAngle(), 0);

        CareTaker.getInstance().createMemento();
        model.moveCannonUp();
        CareTaker.getInstance().createMemento();
        model.aimCannonUp();
        CareTaker.getInstance().createMemento();
        model.moveCannonDown();

        CareTaker.getInstance().setMemento();
        Assert.assertEquals(startingPos.y - MvcGameConfig.MOVE_STEP, model.getCannon().position.y, 0);
        Assert.assertEquals(startingAngle - MvcGameConfig.ANGLE_STEP, model.getCannon().getAngle(), 0);

    }

}
