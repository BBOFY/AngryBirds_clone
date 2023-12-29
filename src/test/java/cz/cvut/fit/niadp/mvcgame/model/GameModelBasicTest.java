package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.eventSystem.EventHolder;
import org.junit.Assert;
import org.junit.Test;

public class GameModelBasicTest {
    @Test
    public void undoLastCommandTest() {
        GameModel model = new GameModel();
        EventHolder.toggleDebugEvent.invoke();

        double positionBeforeUndoY = model.getCannon().position.y;
        model.registerCommand(new MoveCannonUpCmd(model));
        model.update();
        Assert.assertEquals(positionBeforeUndoY - MvcGameConfig.MOVE_STEP, model.getCannon().position.y, 0);
        model.undoLastCommand();
        Assert.assertEquals(positionBeforeUndoY, model.getCannon().position.y, 0);
    }
}
