import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.view.GameView;
import org.junit.Test;

public class GameViewTest {

    @Test
    public void renderNullObjectTest() {
        GameView view = new GameView(new GameModel());
        view.update();
    }

}
