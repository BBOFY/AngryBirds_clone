package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import org.junit.Assert;
import org.junit.Test;

public class GameModelBasicTest {
    @Test
    public void undoLastCommandTest() {
        IGameModel model = new GameModel();

        double positionBeforeUndoY = model.getCannon().position.y;
        model.registerCommand(new MoveCannonUpCmd(model));
        model.update();
        Assert.assertEquals(positionBeforeUndoY - MvcGameConfig.MOVE_STEP, model.getCannon().position.y, 0);
        model.undoLastCommand();
        Assert.assertEquals(positionBeforeUndoY, model.getCannon().position.y, 0);
    }
}
