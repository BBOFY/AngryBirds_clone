package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class GameModelBasicTest {
    @Test
    public void undoLastCommandTest() throws NoSuchFieldException, IllegalAccessException {
        IGameModel model = new GameModel();
        Field cannonField = model.getClass().getDeclaredField("cannon");
        cannonField.setAccessible(true);
        AbsCannon cannon = (AbsCannon) cannonField.get(model);
        double positionBeforeUndoY = cannon.position.y;
        cannonField.setAccessible(false);
        model.registerCommand(new MoveCannonUpCmd(model));
        model.update();
        Assert.assertEquals(positionBeforeUndoY - MvcGameConfig.MOVE_STEP, cannon.position.y, 0);
        model.undoLastCommand();
        Assert.assertEquals(positionBeforeUndoY, cannon.position.y, 0);
        cannonField.setAccessible(false);
    }
}
