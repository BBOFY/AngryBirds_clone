package cz.cvut.fit.niadp.mvcgame.visitor.gui;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

public class GuiRenderer implements IGuiVisitor {

    private String infoText = "";

    private IGameVisuals gv;

    public void setGraphicContext(IGameVisuals gv) {
        this.gv = gv;
    }

    @Override
    public void guiVisitEnemy(Enemy enemy, int health) {
        gv.drawText(Integer.toString(health), enemy.position);
    }

    @Override
    public void guiVisitCannon(AbsCannon cannon, IShootingMode shootingMode) {
        // power
        // angle
        // current shooting mode
    }

    @Override
    public void guiVisitModel(IGameModel model) {
        // n of active missiles
        // n of remaining missiles
        // n of enemies
        // type of current missile type
    }

    @Override
    public void renderInfo() {
        gv.drawText(infoText, new Vector2(0, 0));
    }
}
