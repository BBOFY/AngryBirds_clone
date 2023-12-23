package cz.cvut.fit.niadp.mvcgame.visitor.gui;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameVisuals;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector2;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
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
        infoText += "Power: " + cannon.getPower() + "\n";
        infoText += String.format("Angle: %.2f\n", Math.toDegrees(cannon.getAngle()));
        infoText += "Mode: " + shootingMode.getName() + "\n";
        if (shootingMode instanceof DynamicShootingMode) {
            infoText += "\tMissiles: " + ((DynamicShootingMode) shootingMode).getNumOfShots() + "\n";
        }
    }

    @Override
    public void guiVisitModel(IGameModel model) {
        infoText += "Active missiles: "
                + model.getMissiles().size() + "\n";
        infoText += "Missile type: "
                + model.getMovingStrategyContext().getStrategy().getName() + "\n";
        // n of remaining missiles
        infoText += "Remaining enemies: " + model.getEnemies().size() + "\n";
    }

    @Override
    public void renderInfo() {
        gv.drawText(infoText, new Vector2(0, 15));
        infoText = "";
    }
}
