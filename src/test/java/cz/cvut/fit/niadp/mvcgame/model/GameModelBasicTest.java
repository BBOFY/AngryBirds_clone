package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import org.junit.Test;

import java.lang.reflect.Field;

public class GameModelBasicTest {

    IGameModel model = new GameModel();

    public GameModelBasicTest() throws NoSuchFieldException, IllegalAccessException {
        Field cannon = model.getClass().getDeclaredField("cannon");
        cannon.setAccessible(true);
        AbsCannon c = (AbsCannon) cannon.get(model);
        System.out.println(c.position.y);
    }

//    @Test
//    public void undoLastCommandTest() {
//        IGameModel model = new GameModel();
//        int positionBeforeUndoY = model.getClass().getDeclaredField("cannon").getCannonPosition().getY();
//        model.registerCommand(new MoveCannonUpCommand(model));
//        model.update();
//        Assert.assertEquals(positionBeforeUndoY - MvcGameConfig.MOVE_STEP, model.getCannonPosition().getY());
//        model.undoLastCommand();
//        Assert.assertEquals(positionBeforeUndoY, model.getCannonPosition().getY());
//    }
}
