package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import org.junit.Assert;
import org.junit.Test;

public class GameViewTest {

    @Test
    public void renderNullObjectTest() {
        GameView view = new GameView(new GameModel());
        Assert.assertNotNull(view);
        view.onObjectMoved();
    }

}
