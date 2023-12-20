package cz.cvut.fit.niadp.mvcgame.visitor.gui;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Enemy;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

public interface IGuiVisitor {
    void guiVisitEnemy(Enemy enemy, int health);
    void guiVisitCannon(AbsCannon cannon, IShootingMode shootingMode);
    void guiVisitModel(IGameModel model);

    void renderInfo();

}
